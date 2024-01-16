package com.dfcruz.kitties.ui.screen.kitties

import androidx.lifecycle.ViewModel
import com.dfcruz.model.Breed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class KittiesViewModel @Inject constructor() : ViewModel() {

    private val breedss = List(20) { index ->
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

    private val _breeds = MutableStateFlow(breedss)
    val breeds = _breeds.asStateFlow()
}