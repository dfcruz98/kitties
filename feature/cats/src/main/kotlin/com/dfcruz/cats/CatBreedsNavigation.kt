package com.dfcruz.cats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val catBreedsNavigationRoute = "cat_breeds_route"

fun NavController.catBreedsToKitties(navOptions: NavOptions? = null) {
    this.navigate(catBreedsNavigationRoute, navOptions)
}

fun NavGraphBuilder.catBreedsScreen(onItemClicked: (String) -> Unit) {
    composable(
        route = catBreedsNavigationRoute,
    ) {
        CatBreedsRoute(onItemClicked)
    }
}
