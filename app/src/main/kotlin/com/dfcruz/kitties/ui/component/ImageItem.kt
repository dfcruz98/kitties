package com.dfcruz.kitties.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dfcruz.kitties.ui.theme.KittiesIcons

@Composable
fun ImageItem(
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
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
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
    val icon = if (isFavourite) KittiesIcons.Favourite else KittiesIcons.NotFavourite
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