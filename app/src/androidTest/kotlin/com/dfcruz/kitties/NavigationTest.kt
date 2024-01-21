package com.dfcruz.kitties

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import org.junit.Rule
import org.junit.Test
import kotlin.properties.ReadOnlyProperty

class NavigationTest {

    @JvmField
    @Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun AndroidComposeTestRule<*, *>.stringResource(@StringRes resId: Int) =
        ReadOnlyProperty<Any?, String> { _, _ -> activity.getString(resId) }

    // The strings used for matching in these tests
    private val cats by composeTestRule.stringResource(R.string.cats)
    private val favourites by composeTestRule.stringResource(R.string.favourites)

    @Test
    fun first_screen_is_cats() {
        composeTestRule.apply {
            onNodeWithText(cats).assertIsSelected()
        }
    }

    @Test
    fun navigate_to_favourites() {
        composeTestRule.apply {
            onNodeWithText(favourites).performClick()
            onNodeWithTag("FavouritesScreen").assertIsDisplayed()
        }
    }

    @Test
    fun navigate_to_details() {
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule
                .onAllNodesWithTag("CatItem")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule.apply {
            onAllNodesWithTag("CatItem")
                .onFirst()
                .performClick()

            onNodeWithTag("DetailsScreen").assertIsDisplayed()
        }
    }

    @Test
    fun navigate_back_from_details_to_previous_screen() {
        composeTestRule.apply {

        }
    }

    @Test(expected = NoActivityResumedException::class)
    fun cats_back_quitsApp() {
        composeTestRule.apply {
            onNodeWithText(cats).performClick()
            onNodeWithTag("CatsScreen").assertIsDisplayed()
            Espresso.pressBack()
        }
    }

    @Test(expected = NoActivityResumedException::class)
    fun favourites_back_quitsApp() {
        composeTestRule.apply {
            onNodeWithText(favourites).performClick()
            onNodeWithTag("FavouritesScreen").assertIsDisplayed()
            Espresso.pressBack()
        }
    }
}