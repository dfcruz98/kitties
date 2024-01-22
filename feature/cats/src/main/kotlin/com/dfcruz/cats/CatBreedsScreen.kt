package com.dfcruz.cats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfcruz.designsystem.dimen.Dimen
import com.dfcruz.model.CatBreed
import com.dfcruz.ui.SearchTextField
import com.dfcruz.ui.VerticalGrid

@Composable
fun CatBreedsRoute(
    onItemClicked: (String) -> Unit,
    viewModel: CatBreedsViewModel = hiltViewModel()
) {
    val catBreeds = viewModel.catBreeds.collectAsState()
    val search = viewModel.search.collectAsState()
    val error = viewModel.error.collectAsState()
    val loading = viewModel.loading.collectAsState()

    CatBreedsScreen(
        catBreeds = catBreeds.value,
        error = error.value,
        loading = loading.value,
        search = search.value,
        onItemClicked = onItemClicked, {
            viewModel.toggleFavourite(it)
        },
        onSearchQueryChanged = {
            viewModel.setSearch(it)
        }
    )
}

@Composable
internal fun CatBreedsScreen(
    catBreeds: List<CatBreed>,
    error: String?,
    loading: Boolean,
    search: String,
    onItemClicked: (String) -> Unit,
    onFavouriteClicked: (String) -> Unit,
    onSearchQueryChanged: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .testTag("CatsScreen")
            .fillMaxSize()
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .testTag("LoadingIndicator")
                    .align(Alignment.Center)
            )
        }

        if (error != null) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(
                modifier = Modifier.fillMaxWidth().testTag("CatBreedsGrid")
            ) {
                TopBar(search, onSearchQueryChanged)
                VerticalGrid(
                    catBreeds = catBreeds,
                    onItemClicked = onItemClicked,
                    onFavouriteClicked = onFavouriteClicked
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    search: String,
    onSearchQueryChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(
            horizontal = Dimen.horizontalPadding,
            vertical = Dimen.horizontalPadding
        )
    ) {
        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            searchQuery = search,
            onSearchTriggered = {},
        )
    }
}