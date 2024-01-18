package com.dfcruz.kitties.ui.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.data.repository.CatBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val catBreedsRepository: CatBreedsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val breedId: String = checkNotNull(savedStateHandle[BREED_ID])

    val catBreed = catBreedsRepository.getBreed(breedId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    fun toggleFavourite() {
        viewModelScope.launch {
            catBreedsRepository.toggleBreedFavourite(breedId)
        }
    }
}