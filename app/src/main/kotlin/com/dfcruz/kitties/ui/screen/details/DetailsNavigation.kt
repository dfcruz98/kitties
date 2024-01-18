package com.dfcruz.kitties.ui.screen.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


const val BREED_ID = "breedId"

fun NavController.navigateToDetails(breedId: String) {
    this.navigate("details_route/$breedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.detailsScreen(onBackPressed: () -> Unit) {
    composable(
        route = "details_route/{$BREED_ID}",
        arguments = listOf(
            navArgument(BREED_ID) { type = NavType.StringType },
        ),
    ) {
        DetailsRoute(onBackPressed)
    }
}
