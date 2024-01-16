package com.dfcruz.kitties.ui.screen.kitties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dfcruz.data.repository.BreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KittiesViewModel @Inject constructor(
    breedsRepository: BreedsRepository,
) : ViewModel() {

    val breeds = breedsRepository.getBreeds().cachedIn(viewModelScope)

    /*
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

     */
}