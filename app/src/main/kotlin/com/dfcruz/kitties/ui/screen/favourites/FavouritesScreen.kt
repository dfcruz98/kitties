package com.dfcruz.kitties.ui.screen.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfcruz.kitties.ui.component.KittiesGrid

@Composable
fun FavouritesScreen(
    onItemClicked: (String) -> Unit,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val breeds = viewModel.breeds.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(start = 15.dp, top = 15.dp),
            text = "Favourites",
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .fillMaxWidth()
        )
        KittiesGrid(
            breeds = breeds,
            onItemClicked = onItemClicked,
            onFavourite = {
                viewModel.toggleFavourite(it)
            }
        )
    }

}