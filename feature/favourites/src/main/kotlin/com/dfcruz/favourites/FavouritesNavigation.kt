package com.dfcruz.favourites

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val favouritesNavigationRoute = "favourites_route"

fun NavController.navigateToFavourites(navOptions: NavOptions? = null) {
    this.navigate(com.dfcruz.favourites.favouritesNavigationRoute, navOptions)
}

fun NavGraphBuilder.favouritesScreen(onItemClicked: (String) -> Unit) {
    composable(
        route = com.dfcruz.favourites.favouritesNavigationRoute,
    ) {
        FavouritesRoute(onItemClicked)
    }
}
