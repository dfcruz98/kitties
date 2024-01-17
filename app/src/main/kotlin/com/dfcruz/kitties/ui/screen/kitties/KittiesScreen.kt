package com.dfcruz.kitties.ui.screen.kitties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.dfcruz.kitties.ui.component.ImageItem
import com.dfcruz.kitties.ui.component.KittiesGrid

@Composable
fun KittiesScreen(
    onItemClicked: (String) -> Unit,
    viewModel: KittiesViewModel = hiltViewModel()
) {
    val breeds = viewModel.breeds.collectAsState()
    KittiesGrid(
        breeds = breeds.value,
        onItemClicked = onItemClicked,
        onFavourite = {
            viewModel.toggleFavourite(it)
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        /*
        if (breeds.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        } else {



            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                items(count = breeds.itemCount) { index ->
                    breeds[index]?.let { breed ->
                        ImageItem(
                            image = breed.image.url,
                            isFavourite = breed.favourite,
                            name = breed.name,
                            onClick = {},
                            onFavourite = {
                                viewModel.toggleFavourite(breed.id)
                            }
                        )
                    }
                }


                if (breeds.loadState.append == LoadState.Loading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

         */
    }
}

