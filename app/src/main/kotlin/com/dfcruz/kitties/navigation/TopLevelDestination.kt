package com.dfcruz.kitties.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.dfcruz.kitties.R
import com.dfcruz.kitties.ui.theme.KittiesIcons

enum class TopLevelDestination(
    val icon: ImageVector,
    val iconTextId: Int,
) {
    KITTIES(
        icon = KittiesIcons.Home,
        iconTextId = R.string.kitties,
    ),
    FAVOURITES(
        icon = KittiesIcons.Favourite,
        iconTextId = R.string.favourites,
    ),
}


