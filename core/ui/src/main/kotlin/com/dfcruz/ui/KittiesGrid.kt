package com.dfcruz.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dfcruz.designsystem.dimen.Dimen
import com.dfcruz.model.CatBreed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit

@Composable
fun VerticalGrid(
    catBreeds: List<CatBreed>,
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit,
    onFavouriteClicked: (String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(Dimen.gridSpace),
        horizontalArrangement = Arrangement.spacedBy(Dimen.gridSpace),
    ) {
        items(catBreeds, key = { it.id }) {
            ImageItem(
                image = it.image.url,
                favourite = it.favourite,
                displayText = it.name,
                onClick = {
                    onItemClicked(it.id)
                },
                onFavourite = {
                    onFavouriteClicked(it.id)
                }
            )
        }
    }
}

@Composable
@Preview
private fun VerticalGridPreview() {
    val catBreeds = listOf(
        CatBreed(
            id = "1",
            name = "Bengal",
            temperament = "",
            origin = "",
            description = "",
            countryCodes = "",
            countryCode = "",
            lifeSpan = "",
            wikipediaUrl = "",
            image = Image(
                id = "",
                width = 1,
                height = 1,
                url = "https://cdn2.thecatapi.com/images/werXZVLvS.jpg"
            ),
            weight = MassUnit(
                imperial = "",
                metric = ""
            )
        )
    )
    VerticalGrid(catBreeds = catBreeds, onItemClicked = {}, onFavouriteClicked = {})
}