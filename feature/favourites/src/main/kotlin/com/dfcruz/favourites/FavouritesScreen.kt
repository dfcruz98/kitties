package com.dfcruz.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfcruz.model.CatBreed
import com.dfcruz.ui.VerticalGrid

@Composable
fun FavouritesRoute(
    onItemClicked: (String) -> Unit,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val catBreeds = viewModel.breeds.collectAsState().value
    val error = viewModel.error.collectAsState()
    val loading = viewModel.loading.collectAsState()

    FavouritesScreen(
        catBreeds = catBreeds,
        error = error.value,
        loading = loading.value,
        onItemClicked = onItemClicked,
        onFavouriteClicked = {
            viewModel.toggleFavourite(it)
        }
    )
}

@Composable
internal fun FavouritesScreen(
    catBreeds: List<CatBreed>,
    error: String?,
    loading: Boolean,
    onItemClicked: (String) -> Unit,
    onFavouriteClicked: (String) -> Unit,
) {

    Box {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("LoadingIndicator")
            )
        }

        if (error != null) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            VerticalGrid(
                catBreeds = catBreeds,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("FavouritesGrid"),
                onItemClicked = onItemClicked,
                onFavouriteClicked = onFavouriteClicked
            )
        }
    }
}


@Preview
@Composable
private fun FavouritesScreenPreview() {
    FavouritesScreen(
        catBreeds = listOf(),
        onItemClicked = {},
        loading = false,
        error = "",
        onFavouriteClicked = {})
}