package com.dfcruz.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfcruz.designsystem.dimen.Dimen
import com.dfcruz.model.CatBreed
import com.dfcruz.ui.VerticalGrid

@Composable
fun FavouritesRoute(
    onItemClicked: (String) -> Unit,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val catBreeds = viewModel.breeds.collectAsState().value
    FavouritesScreen(
        catBreeds = catBreeds,
        onItemClicked = onItemClicked,
        onFavouriteClicked = {
            viewModel.toggleFavourite(it)
        }
    )
}

@Composable
fun FavouritesScreen(
    catBreeds: List<CatBreed>,
    onItemClicked: (String) -> Unit,
    onFavouriteClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = Dimen.largePadding,
                    top = Dimen.largePadding
                ),
            text = stringResource(R.string.favourites),
            style = MaterialTheme.typography.displaySmall
        )
        VerticalGrid(
            catBreeds = catBreeds,
            onItemClicked = onItemClicked,
            onFavouriteClicked = onFavouriteClicked
        )
    }
}


@Preview
@Composable
private fun FavouritesScreenPreview() {
    FavouritesScreen(catBreeds = listOf(), onItemClicked = {}, onFavouriteClicked = {})
}