package com.dfcruz.favourites

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.data.repository.CatBreedsRepository
import com.dfcruz.model.CatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val catBreedsRepository: CatBreedsRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    val favouritesData = catBreedsRepository.getFavouriteBreeds()
        .onStart {
            _error.value = null
        }
        .catch {
            _loading.value = false
            _error.value = "Error loading cat details"
        }
        .onEach {
            _loading.value = false
        }.map {
            FavouritesData(
                favourites = it,
                averageLifespan = computeAverageLifespan(it)
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    fun toggleFavourite(id: String) {
        viewModelScope.launch {
            catBreedsRepository.toggleBreedFavourite(id)
        }
    }

    private fun computeAverageLifespan(favourites: List<CatBreed>): Int {
        val total = favourites.sumOf { it.lifeSpan.split("-").first().trim().toInt() }
        return total / favourites.size
    }
}

@Immutable
data class FavouritesData(
    val favourites: List<CatBreed>,
    val averageLifespan: Int,
)