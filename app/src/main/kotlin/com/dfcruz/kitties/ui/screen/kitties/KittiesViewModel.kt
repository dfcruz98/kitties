package com.dfcruz.kitties.ui.screen.kitties

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class KittiesViewModel @Inject constructor() : ViewModel() {

    private val kittens = List(20) { index ->
        Kitty(
            id = "$index",
            name = "Bengal",
            "https://cdn2.thecatapi.com/images/werXZVLvS.jpg",
            false,
        )
    }

    private val _kitties = MutableStateFlow(kittens)
    val kitties = _kitties.asStateFlow()
}


@Immutable
data class Kitty(
    val id: String,
    val name: String,
    val image: String,
    val favourite: Boolean
)
