package com.dfcruz.details

import androidx.lifecycle.SavedStateHandle
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

class DetailsViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var SUT: DetailsViewModel
    private val savedStateHandle = SavedStateHandle()
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
        savedStateHandle[BREED_ID] = "abys"
        SUT = DetailsViewModel(
            catBreedsRepository = catBreedsRepository,
            savedStateHandle = savedStateHandle,
        )
    }

    @Test
    fun check_initial_state() = runTest {
        assertThat(SUT.catBreed.value).isNull()
        assertThat(SUT.error.value).isNull()
    }

    @Test
    fun fetch_details() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { SUT.catBreed.collect() }

        catBreedsRepository.setValue(defaultCatBreed)

        assertThat(SUT.catBreed.value).isEqualTo(defaultCatBreed)

        collectJob.cancel()
    }
}