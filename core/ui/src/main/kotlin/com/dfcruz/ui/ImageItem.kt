package com.dfcruz.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.dfcruz.designsystem.dimen.Dimen
import com.dfcruz.designsystem.icon.KittiesIcons

@Composable
fun ImageItem(
    image: String,
    favourite: Boolean,
    displayText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onFavourite: () -> Unit,
) {
    Surface(
        modifier = modifier
            .testTag("CatItem")
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
                    contentDescription = stringResource(R.string.cat_image),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = displayText,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            FavouriteIcon(
                isFavourite = favourite,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = Dimen.mediumPadding, end = Dimen.mediumPadding),
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
    if (isFavourite) {
        Icon(
            imageVector = KittiesIcons.Favourite,
            contentDescription = null,
            modifier = modifier.clickable {
                onClick()
            },
            tint = Color.Red
        )
    } else {
        Icon(
            imageVector = KittiesIcons.NotFavourite,
            contentDescription = null,
            modifier = modifier.clickable {
                onClick()
            }
        )
    }
}

@Composable
@Preview
private fun ImageItemPreview() {
    ImageItem(
        image = "",
        favourite = false,
        displayText = "Bengal",
        onClick = {

        },
        onFavourite = {

        }
    )
}