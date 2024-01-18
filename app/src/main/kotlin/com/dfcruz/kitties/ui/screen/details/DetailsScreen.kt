package com.dfcruz.kitties.ui.screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dfcruz.kitties.R
import com.dfcruz.kitties.ui.theme.KittiesIcons
import com.dfcruz.model.CatBreed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit

@Composable
fun DetailsRoute(
    onBackPressed: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val catBreed = viewModel.catBreed.collectAsState().value
    catBreed?.let {
        DetailsScreen(it,
            onBackPressed = onBackPressed,
            onFavouriteToggle = {
                viewModel.toggleFavourite()
            }
        )
    }
}

@Composable
fun DetailsScreen(
    catBreed: CatBreed,
    onBackPressed: () -> Unit,
    onFavouriteToggle: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {


        /*
        FilledIconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 15.dp, top = 10.dp),
            onClick = onFavouriteToggle
        ) {
            Icon(
                imageVector = if (breed.favourite) KittiesIcons.Favourite else KittiesIcons.NotFavourite,
                contentDescription = null
            )
        }

         */

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxHeight(),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.40f),
                model = catBreed.image.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()

            )

            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                Text(
                    text = catBreed.name,
                    style = MaterialTheme.typography.displaySmall
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = stringResource(R.string.origin),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = catBreed.origin,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = stringResource(R.string.temperament),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = catBreed.temperament,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = stringResource(R.string.about),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = catBreed.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
            }

        }

        FilledIconButton(
            modifier = Modifier
                .padding(start = 15.dp, top = 10.dp)
                .align(Alignment.TopStart),
            onClick = onBackPressed
        ) {
            Icon(
                imageVector = KittiesIcons.ArrowBack,
                contentDescription = null
            )
        }

        FilledIconButton(
            modifier = Modifier
                .padding(end = 15.dp, top = 10.dp)
                .align(Alignment.TopEnd),
            onClick = onFavouriteToggle
        ) {
            Icon(
                imageVector = if (catBreed.favourite) KittiesIcons.Favourite else KittiesIcons.NotFavourite,
                contentDescription = null
            )
        }

    }
}

@Composable
@Preview
fun DetailsScreenPreview() {
    val catBreed = CatBreed(
        id = "abys",
        name = "Abyssinian",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle",
        origin = "Egypt",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        countryCodes = "EG",
        countryCode = "EG",
        lifeSpan = "14 - 15",
        wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
        image = Image(
            id = "0XYvRd7oD",
            width = 1204,
            height = 1445,
            url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
        ),
        weight = MassUnit(
            imperial = "7  -  10",
            metric = "3 - 5"
        )
    )

    DetailsScreen(catBreed = catBreed, onBackPressed = {}, onFavouriteToggle = {})
}