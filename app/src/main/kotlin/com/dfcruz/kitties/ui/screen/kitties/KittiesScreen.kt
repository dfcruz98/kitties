package com.dfcruz.kitties.ui.screen.kitties

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfcruz.kitties.ui.component.KittiesGrid

@Composable
fun KittiesScreen(
    viewModel: KittiesViewModel = hiltViewModel()
) {
    val kitties = viewModel.breeds.collectAsState().value
    KittiesGrid(kitties, onClick = {}, onFavourite = {})
}

