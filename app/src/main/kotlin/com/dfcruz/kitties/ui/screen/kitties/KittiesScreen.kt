package com.dfcruz.kitties.ui.screen.kitties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfcruz.kitties.ui.component.ImageItem
import com.dfcruz.kitties.ui.component.KittiesGrid

@Composable
fun KittiesScreen(
    viewModel: KittiesViewModel = hiltViewModel()
) {
    val kitties = viewModel.kitties.collectAsState().value
    KittiesGrid(kitties, onClick = {}, onFavourite = {})
}

