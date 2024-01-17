package com.dfcruz.kitties.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfcruz.model.Breed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit

@Composable
fun KittiesGrid(
    breeds: List<Breed>,
    onItemClicked: (String) -> Unit,
    onFavourite: (String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(breeds, key = { it.id }) {
            ImageItem(
                image = it.image.url,
                isFavourite = it.favourite,
                name = it.name,
                onClick = {
                    onItemClicked(it.id)
                },
                onFavourite = {
                    onFavourite(it.id)
                }
            )
        }
    }
}

@Composable
@Preview
private fun KittiesGridPreview() {
    val breeds = listOf(
        Breed(
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
    KittiesGrid(breeds, {}, {})
}