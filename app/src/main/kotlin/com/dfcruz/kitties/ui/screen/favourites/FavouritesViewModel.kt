package com.dfcruz.kitties.ui.screen.favourites

import androidx.lifecycle.ViewModel
import com.dfcruz.kitties.ui.screen.kitties.Kitty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor() : ViewModel() {

    private val kittens = List(1) { index ->
        Kitty(
            id = "$index",
            name = "Bengal",
            "https://cdn2.thecatapi.com/images/werXZVLvS.jpg",
            true,
        )
    }

    private val _kitties = MutableStateFlow(kittens)
    val kitties = _kitties.asStateFlow()

}