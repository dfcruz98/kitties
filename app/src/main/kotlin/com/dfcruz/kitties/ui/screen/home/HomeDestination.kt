package com.dfcruz.kitties.ui.screen.home

import androidx.compose.ui.graphics.vector.ImageVector
import com.dfcruz.designsystem.icon.KittiesIcons
import com.dfcruz.kitties.R

enum class HomeDestination(
    val icon: ImageVector,
    val iconTextId: Int,
) {
    CAT_BREEDS(
        icon = KittiesIcons.Home,
        iconTextId = R.string.cats,
    ),
    FAVOURITES(
        icon = KittiesIcons.Favourite,
        iconTextId = R.string.favourites,
    ),
}


