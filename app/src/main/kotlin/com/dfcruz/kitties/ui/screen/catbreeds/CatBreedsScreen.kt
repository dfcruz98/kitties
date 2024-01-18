package com.dfcruz.kitties.ui.screen.catbreeds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dfcruz.kitties.R
import com.dfcruz.kitties.ui.component.ImageItem
import com.dfcruz.kitties.ui.component.SearchTextField
import com.dfcruz.kitties.ui.component.VerticalGrid
import com.dfcruz.kitties.ui.theme.Dimen
import com.dfcruz.model.CatBreed

@Composable
fun CatBreedsRoute(
    onItemClicked: (String) -> Unit,
    viewModel: CatBreedsViewModel = hiltViewModel()
) {
    //val pagingItems = viewModel.catBreeds.collectAsLazyPagingItems()
    val catBreeds = viewModel.catBreeds.collectAsState()
    val search = viewModel.search.collectAsState()
    CatBreedsScreen(
        catBreeds = catBreeds.value,
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
fun CatBreedsScreen(
    //catBreeds: LazyPagingItems<CatBreed>,
    catBreeds: List<CatBreed>,
    search: String,
    onItemClicked: (String) -> Unit,
    onFavouriteClicked: (String) -> Unit,
    onSearchQueryChanged: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TopBar(search, onSearchQueryChanged)
            VerticalGrid(
                catBreeds = catBreeds,
                onItemClicked = onItemClicked,
                onFavouriteClicked = onFavouriteClicked
            )
            /*
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                items(count = catBreeds.itemCount) { index ->
                    catBreeds[index]?.let { breed ->
                        ImageItem(
                            image = breed.image.url,
                            favourite = breed.favourite,
                            displayText = breed.name,
                            onClick = {
                                onItemClicked(breed.id)
                            },
                            onFavourite = {
                                onFavourite(breed.id)
                            }
                        )
                    }
                }

                if (catBreeds.loadState.append == LoadState.Loading) {
                    item(
                        /*
                        span = {
                            GridItemSpan(maxCurrentLineSpan + 1)
                        }

                         */
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

             */
        }
    }
}

@Composable
private fun TopBar(
    search: String,
    onSearchQueryChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = Dimen.horizontalPadding, vertical = Dimen.horizontalPadding)
    ) {
        /*
        Text(
            modifier = Modifier.padding(start = Dimen.mediumPadding, top = Dimen.largePadding),
            text = stringResource(R.string.cats),
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(
            modifier = Modifier
                .height(Dimen.mediumHeight)
                .fillMaxWidth()
        )
         */
        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            searchQuery = search,
            onSearchTriggered = {},
        )

        /*
        Spacer(
            modifier = Modifier
                .height(Dimen.mediumHeight)
                .fillMaxWidth()
        )

         */

    }
}

@Preview
@Composable
private fun KittiesScreenPreview() {
    //CatBreedsScreen(flowOf<CatBreed>(), {}, {})
}

