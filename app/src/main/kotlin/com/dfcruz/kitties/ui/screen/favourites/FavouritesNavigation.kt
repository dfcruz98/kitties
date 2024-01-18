package com.dfcruz.kitties.ui.screen.favourites

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val favouritesNavigationRoute = "favourites_route"

fun NavController.navigateToFavourites(navOptions: NavOptions? = null) {
    this.navigate(favouritesNavigationRoute, navOptions)
}

fun NavGraphBuilder.favouritesScreen(onItemClicked: (String) -> Unit) {
    composable(
        route = favouritesNavigationRoute,
    ) {
        FavouritesRoute(onItemClicked)
    }
}
