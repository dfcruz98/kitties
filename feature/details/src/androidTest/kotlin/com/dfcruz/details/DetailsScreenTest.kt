package com.dfcruz.details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.dfcruz.model.CatBreed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val catBreedTestData = CatBreed(
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

    @Test
    fun back_button_is_visible() {
        composeTestRule.setContent {
            DetailsScreen(
                catBreed = catBreedTestData,
                error = null,
                onBackPressed = {},
                onFavouriteToggle = {}
            )
        }

        composeTestRule
            .onNodeWithTag("BackButton")
            .assertIsDisplayed()
    }

    @Test
    fun error_message_shown_on_error() {
        val errorMessage = "Unable to get cat details"
        composeTestRule.setContent {
            DetailsScreen(
                catBreed = catBreedTestData,
                error = errorMessage,
                onBackPressed = {},
                onFavouriteToggle = {}
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertIsDisplayed()
    }

    @Test
    fun cat_details_are_shown() {
        composeTestRule.setContent {
            DetailsScreen(
                catBreed = catBreedTestData,
                error = null,
                onBackPressed = {},
                onFavouriteToggle = {}
            )
        }

        composeTestRule.onNodeWithText(catBreedTestData.name).assertExists()
        composeTestRule.onNodeWithText(catBreedTestData.origin).assertExists()
        composeTestRule.onNodeWithText(catBreedTestData.temperament).assertExists()
        composeTestRule.onNodeWithText(catBreedTestData.description).assertExists()
    }

}