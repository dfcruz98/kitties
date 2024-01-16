package com.dfcruz.kitties.ui.screen.favourites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfcruz.kitties.ui.component.KittiesGrid

@Composable
fun FavouritesScreen(viewModel: FavouritesViewModel = hiltViewModel()) {
    val breeds = viewModel.breeds.collectAsState().value
    KittiesGrid(breeds, onClick = {}, onFavourite = {})
}