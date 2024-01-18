package com.dfcruz.kitties.ui.screen.catbreeds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dfcruz.kitties.ui.component.KittiesGrid
import com.dfcruz.kitties.ui.component.SearchTextField
import com.dfcruz.model.CatBreed

@Composable
fun KittiesRoute(
    onItemClicked: (String) -> Unit,
    viewModel: CatBreedsViewModel = hiltViewModel()
) {
    val breeds = viewModel.catBreeds.collectAsState()
    KittiesScreen(breeds.value, onItemClicked) {
        viewModel.toggleFavourite(it)
    }
}

@Composable
fun KittiesScreen(
    catBreeds: List<CatBreed>,
    onItemClicked: (String) -> Unit,
    onFavourite: (String) -> Unit,
) {

    Column {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 15.dp, top = 15.dp),
                text = "Cats",
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
            )

            SearchTextField(
                onSearchQueryChanged = {},
                searchQuery = "",
                onSearchTriggered = {},
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
            )

        }

        KittiesGrid(
            catBreeds = catBreeds,
            onItemClicked = onItemClicked,
            onFavourite = onFavourite
        )

    }



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

@Preview
@Composable
private fun KittiesScreenPreview() {
    KittiesScreen(listOf(), {}, {})
}

