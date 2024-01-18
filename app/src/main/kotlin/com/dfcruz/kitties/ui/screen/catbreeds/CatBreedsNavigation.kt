package com.dfcruz.kitties.ui.screen.catbreeds

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val kittiesNavigationRoute = "kitties_route"

fun NavController.navigateToKitties(navOptions: NavOptions? = null) {
    this.navigate(kittiesNavigationRoute, navOptions)
}

fun NavGraphBuilder.kittiesScreen(onItemClicked: (String) -> Unit) {
    composable(
        route = kittiesNavigationRoute,
    ) {
        KittiesRoute(onItemClicked)
    }
}
