package com.dfcruz.kitties.ui.screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dfcruz.kitties.R
import com.dfcruz.kitties.ui.component.KittiesGrid
import com.dfcruz.kitties.ui.screen.favourites.FavouritesViewModel
import com.dfcruz.kitties.ui.theme.KittiesIcons
import com.dfcruz.model.Breed

@Composable
fun BananasScreen(
    onItemClicked: (String) -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    /*
    val breeds = viewModel.breeds.collectAsState().value
    KittiesGrid(
        breeds = breeds,
        onItemClicked = onItemClicked,
        onFavourite = {
            viewModel.toggleFavourite(it)
        }
    )
    
     */

    val state = viewModel.breed.collectAsState().value
    state?.let { 
        Text(text = "Breed=${it.name}")
    }

}

@Composable
fun DetailsScreen2(
    breed: Breed,
    onBackPressed: () -> Unit,
    onFavouriteToggle: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
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
                    .align(Alignment.TopEnd)
                    .padding(end = 15.dp, top = 10.dp),
                onClick = onFavouriteToggle
            ) {

            }

            Column {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.40f),
                    model = breed.image.url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                )

                Column(
                    modifier = Modifier.padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = breed.name,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = stringResource(R.string.origin),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = breed.origin,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = stringResource(R.string.temperament),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = breed.temperament,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = stringResource(R.string.about),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = breed.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }

        }
    }
}
