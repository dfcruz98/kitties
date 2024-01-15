package com.dfcruz.kitties.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun KittiesScreen(
    viewModel: KittiesViewModel = hiltViewModel()
) {
    val kitties = viewModel.kitties.collectAsState().value

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(kitties, key = { it.id }) {
            ImageItem(
                image = it.image,
                isFavourite = it.favourite,
                name = it.name,
                onClick = {

                },
                onFavourite = {

                }
            )
        }
    }
}


@Composable
private fun ImageItem(
    image: String,
    isFavourite: Boolean,
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onFavourite: () -> Unit,
) {
    Surface(
        modifier = modifier
            .width(100.dp)
            .clickable {
                onClick()
            },
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .aspectRatio(1f),
                    model = image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = name,
                    textAlign = TextAlign.Center
                )
            }
            FavouriteIcon(
                isFavourite = isFavourite,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 5.dp, end = 5.dp),

                onClick = onFavourite
            )
        }
    }
}

@Composable
private fun FavouriteIcon(
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val icon = if (isFavourite) Icons.Filled.Star else Icons.Outlined.Star
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = modifier.clickable {
            onClick()
        }
    )
}

@Composable
@Preview
private fun ImageItemPreview() {
    ImageItem(
        image = "",
        isFavourite = false,
        name = "Bengal",
        onClick = {

        },
        onFavourite = {

        }
    )
}

