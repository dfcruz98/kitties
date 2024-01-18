package com.dfcruz.kitties.ui.screen.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.data.repository.CatBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val catBreedsRepository: CatBreedsRepository,
) : ViewModel() {

    val breeds = catBreedsRepository.getFavouriteBreeds()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf(),
        )

    fun toggleFavourite(id: String) {
        viewModelScope.launch {
            catBreedsRepository.toggleBreedFavourite(id)
        }
    }
}