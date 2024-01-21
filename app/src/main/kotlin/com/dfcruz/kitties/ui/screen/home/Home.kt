package com.dfcruz.kitties.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.dfcruz.cats.catBreedsNavigationRoute
import com.dfcruz.cats.catBreedsScreen
import com.dfcruz.cats.catBreedsToKitties
import com.dfcruz.favourites.favouritesScreen
import com.dfcruz.favourites.navigateToFavourites

const val homeNavigationRoute = "home_route"

fun NavGraphBuilder.homeGraph(onNavigateTodDetails: (String) -> Unit) {
    composable(route = homeNavigationRoute) {
        val navController = rememberNavController()
        val screens =
            remember { listOf(HomeDestination.CAT_BREEDS, HomeDestination.FAVOURITES) }

        Scaffold(
            bottomBar = {
                KittiesBottomNavBar(navController, screens)
            }
        ) { padding ->

            NavHost(
                navController = navController,
                startDestination = catBreedsNavigationRoute,
                modifier = Modifier.padding(padding)
            ) {
                catBreedsScreen {
                    onNavigateTodDetails(it)
                }
                favouritesScreen {
                    onNavigateTodDetails(it)
                }
            }
        }
    }
}

@Composable
fun KittiesBottomNavBar(
    navController: NavController,
    homeDestinations: List<HomeDestination>
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        homeDestinations.forEach { destination ->
            NavigationBarItem(
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) },
                selected = currentDestination?.isTopLevelDestinationInHierarchy(destination) == true,
                onClick = {
                    val topLevelNavOptions = navOptions {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }

                    when (destination) {
                        HomeDestination.CAT_BREEDS -> navController.catBreedsToKitties(
                            topLevelNavOptions
                        )

                        HomeDestination.FAVOURITES -> navController.navigateToFavourites(
                            topLevelNavOptions
                        )
                    }
                }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: HomeDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false