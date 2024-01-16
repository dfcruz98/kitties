package com.dfcruz.kitties.ui.screen.favourites

import androidx.lifecycle.ViewModel
import com.dfcruz.model.Breed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor() : ViewModel() {

    private val kittens = List(1) { index ->
        Breed(
            id = "$index",
            name = "Bengal",
            temperament = "",
            origin = "",
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
    }

    private val _breeds = MutableStateFlow(kittens)
    val breeds = _breeds.asStateFlow()

}