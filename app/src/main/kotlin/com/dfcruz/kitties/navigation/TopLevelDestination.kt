package com.dfcruz.kitties.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.dfcruz.kitties.R

enum class TopLevelDestination(
    val icon: ImageVector,
    val iconTextId: Int,
) {
    KITTIES(
        icon = Icons.Filled.Star,
        iconTextId = R.string.kitties,
    ),
}


