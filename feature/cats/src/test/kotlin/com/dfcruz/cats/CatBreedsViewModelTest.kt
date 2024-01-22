package com.dfcruz.cats

import com.dfcruz.model.CatBreed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit
import com.dfcruz.testing.repository.TestCatBreedsRepository
import com.dfcruz.testing.rule.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatBreedsViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

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

    private lateinit var SUT: CatBreedsViewModel
    private val catBreedsRepository = TestCatBreedsRepository()

    @Before
    fun setup() {
        SUT = CatBreedsViewModel(
            catBreedsRepository = catBreedsRepository,
        )
    }

    @Test
    fun check_initial_state() = runTest {
        assertThat(SUT.catBreeds.value.size).isEqualTo(0)
        assertThat(SUT.error.value).isNull()
        assertThat(SUT.loading.value).isEqualTo(true)
        assertThat(SUT.search.value).isEqualTo("")
    }

    @Test
    fun get_cats_without_filter() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { SUT.catBreeds.collect() }

        SUT.setSearch("")

        catBreedsRepository.emit(
            listOf(
                defaultCatBreed,
                defaultCatBreed
            )
        )

        delay(SEARCH_DEBOUNCE_TIME)

        assertThat(SUT.catBreeds.value.size).isEqualTo(2)
        assertThat(SUT.loading.value).isEqualTo(false)
        assertThat(SUT.search.value).isEqualTo("")

        collectJob.cancel()
    }

    @Test
    fun filter_cats() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { SUT.catBreeds.collect() }

        SUT.setSearch("american")

        catBreedsRepository.emit(
            listOf(
                defaultCatBreed,
                defaultCatBreed.copy(name = "American short hair")
            )
        )

        delay(SEARCH_DEBOUNCE_TIME)

        assertThat(SUT.catBreeds.value.size).isEqualTo(1)
        assertThat(SUT.loading.value).isEqualTo(false)
        assertThat(SUT.search.value).isEqualTo("american")

        collectJob.cancel()
    }

}