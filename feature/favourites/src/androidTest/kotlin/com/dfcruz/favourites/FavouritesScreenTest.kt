package com.dfcruz.favourites

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.dfcruz.model.CatBreed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit
import org.junit.Rule
import org.junit.Test

class FavouritesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val favouritesTestData = CatBreed(
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
    fun loading_indicator_visible_while_loading_data() {
        composeTestRule.setContent {
            BoxWithConstraints {
                FavouritesScreen(
                    catBreeds = null,
                    error = null,
                    loading = true,
                    onItemClicked = {},
                    onFavouriteClicked = {}
                )
            }
        }

        composeTestRule
            .onNodeWithTag("LoadingIndicator")
            .assertIsDisplayed()
    }

    @Test
    fun error_message_visible() {
        val errorMessage = "Unable to get favourites"
        composeTestRule.setContent {
            BoxWithConstraints {
                FavouritesScreen(
                    catBreeds = null,
                    error = errorMessage,
                    loading = true,
                    onItemClicked = {},
                    onFavouriteClicked = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertIsDisplayed()
    }

    @Test
    fun show_cat_grid() {
        composeTestRule.setContent {
            BoxWithConstraints {
                FavouritesScreen(
                    catBreeds = FavouritesData(
                        favourites = listOf(favouritesTestData),
                        averageLifespan = 0
                    ),
                    error = null,
                    loading = true,
                    onItemClicked = {},
                    onFavouriteClicked = {}
                )
            }
        }

        composeTestRule
            .onNodeWithTag("FavouritesGrid")
            .assertIsDisplayed()
    }
}