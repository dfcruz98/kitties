package com.dfcruz.favourites

import com.dfcruz.model.CatBreed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit
import com.dfcruz.testing.repository.TestCatBreedsRepository
import com.dfcruz.testing.rule.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavouritesViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var SUT: FavouritesViewModel
    private val catBreedsRepository = TestCatBreedsRepository()

    private val defaultCatBreed = CatBreed(
        id = "abys",
        name = "Abyssinian",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle",
        origin = "Egypt",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        countryCodes = "EG",
        countryCode = "EG",
        lifeSpan = "14 - 15",
        wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
        image = Image(
            id = "0XYvRd7oD",
            width = 1204,
            height = 1445,
            url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
        ),
        weight = MassUnit(
            imperial = "7  -  10",
            metric = "3 - 5"
        )
    )

    @Before
    fun setup() {
        SUT = FavouritesViewModel(
            catBreedsRepository = catBreedsRepository,
        )
    }

    @Test
    fun check_initial_state() = runTest {
        assertThat(SUT.favouritesData.value).isNull()
        assertThat(SUT.loading.value).isEqualTo(true)
        assertThat(SUT.error.value).isNull()
    }

    @Test
    fun get_favourites() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { SUT.favouritesData.collect() }

        catBreedsRepository.emit(
            listOf(
                defaultCatBreed.copy(favourite = false),
                defaultCatBreed.copy(favourite = true),
                defaultCatBreed.copy(favourite = true)
            )
        )

        assertThat(SUT.favouritesData.value?.favourites?.size).isEqualTo(2)
        assertThat(SUT.favouritesData.value?.favourites?.first()?.favourite).isEqualTo(true)

        collectJob.cancel()
    }

    @Test
    fun calculate_average() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { SUT.favouritesData.collect() }

        catBreedsRepository.emit(
            listOf(
                defaultCatBreed.copy(favourite = true, lifeSpan = "15 - 20"),
                defaultCatBreed.copy(favourite = true, lifeSpan = "17 - 20")
            )
        )

        assertThat(SUT.favouritesData.value?.averageLifespan).isEqualTo(16)

        collectJob.cancel()
    }

}