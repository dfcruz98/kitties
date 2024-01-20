package com.dfcruz.favourites

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
fun FavouritesScreen(
    catBreeds: List<CatBreed>,
    error: String?,
    loading: Boolean,
    onItemClicked: (String) -> Unit,
    onFavouriteClicked: (String) -> Unit,
) {

    Box {
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (error != null) {
            Toast.makeText(
                LocalContext.current,
                error,
                Toast.LENGTH_LONG
            ).show()
        } else {
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