package com.dfcruz.cats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.data.repository.CatBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SEARCH_DEBOUNCE_TIME = 300L

@OptIn(FlowPreview::class)
@HiltViewModel
class CatBreedsViewModel @Inject constructor(
    private val catBreedsRepository: CatBreedsRepository,
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = "",
        )

    val catBreeds = search
        .debounce(SEARCH_DEBOUNCE_TIME) // Give a time delay to allow the user to insert more digits
        .combine(catBreedsRepository.getBreeds()) { filter, catBreeds ->
            if (filter.isBlank()) return@combine catBreeds
            catBreeds.filter { it.name.uppercase().contains(filter.trim().uppercase()) }
        }
        .onStart {
            _error.value = null
        }
        .catch {
            _loading.value = false
            _error.value = "Error loading cat details"
        }
        .onEach {
            _loading.value = false
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = listOf(),
        )

    fun setSearch(filter: String) {
        _search.value = filter
    }

    fun toggleFavourite(id: String) {
        viewModelScope.launch {
            catBreedsRepository.toggleBreedFavourite(id)
        }
    }
}