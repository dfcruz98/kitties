package com.dfcruz.kitties.ui.screen.kitties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.data.repository.BreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KittiesViewModel @Inject constructor(
    private val breedsRepository: BreedsRepository,
) : ViewModel() {

    val breeds = breedsRepository.getBreeds()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf(),
        )

    fun toggleFavourite(id: String) {
        viewModelScope.launch {
            breedsRepository.toggleBreedFavourite(id)
        }
    }
}