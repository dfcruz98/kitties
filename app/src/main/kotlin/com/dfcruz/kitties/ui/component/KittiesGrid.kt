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
import com.dfcruz.kitties.ui.screen.kitties.Kitty

@Composable
fun KittiesGrid(
    kitties: List<Kitty>,
    onClick: () -> Unit,
    onFavourite: () -> Unit,
) {
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
                onClick = onClick,
                onFavourite = onFavourite
            )
        }
    }
}

@Composable
@Preview
private fun KittiesGridPreview() {
    val kitties = listOf(
        Kitty(
            id = "1",
            name = "Bengal",
            "https://cdn2.thecatapi.com/images/werXZVLvS.jpg",
            false,
        ),
        Kitty(
            id = "2",
            name = "Bengal",
            "https://cdn2.thecatapi.com/images/werXZVLvS.jpg",
            false,
        ),
    )
    KittiesGrid(kitties, {}, {})
}