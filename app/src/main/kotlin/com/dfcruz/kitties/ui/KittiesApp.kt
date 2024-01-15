package com.dfcruz.kitties.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.dfcruz.kitties.navigation.TopLevelDestination
import com.dfcruz.kitties.ui.screen.kitties.kittiesNavigationRoute
import com.dfcruz.kitties.ui.screen.kitties.kittiesScreen
import com.dfcruz.kitties.ui.screen.kitties.navigateToKitties

@Composable
fun KittiesApp() {
    val navController = rememberNavController()
    val screens = remember { listOf(TopLevelDestination.KITTIES) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                KittiesBottomNavBar(navController, screens)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = kittiesNavigationRoute,
                modifier = Modifier.padding(innerPadding)
            ) {
                kittiesScreen()
            }
        }
    }
}

@Composable
fun KittiesBottomNavBar(
    navController: NavController,
    topLevelDestinations: List<TopLevelDestination>
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        topLevelDestinations.forEach { destination ->
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
                        TopLevelDestination.KITTIES -> navController.navigateToKitties(
                            topLevelNavOptions
                        )
                    }
                }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
