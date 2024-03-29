package com.dfcruz.kitties.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dfcruz.details.detailsScreen
import com.dfcruz.details.navigateToDetails
import com.dfcruz.kitties.ui.screen.home.homeGraph
import com.dfcruz.kitties.ui.screen.home.homeNavigationRoute

@Composable
fun KittiesApp() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = homeNavigationRoute,
        ) {
            homeGraph {
                navController.navigateToDetails(it)
            }
            detailsScreen { navController.popBackStack() }
        }
    }
}
