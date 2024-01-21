package com.dfcruz.testing.repository

import com.dfcruz.data.repository.CatBreedsRepository
import com.dfcruz.model.CatBreed
import com.dfcruz.model.Image
import com.dfcruz.model.MassUnit
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class TestCatBreedsRepository : CatBreedsRepository {

    val defaultCatBreed = CatBreed(
        id = "abys",
        name = "Abyssinian",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle",
        origin = "Egypt",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        countryCodes = "EG",
        countryCode = "EG",
        lifeSpan = "14 - 15",
        wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
        image = Image(
            id = "0XYvRd7oD",
            width = 1204,
            height = 1445,
            url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
        ),
        weight = MassUnit(
            imperial = "7  -  10",
            metric = "3 - 5"
        )
    )

    private val _catBreeds =
        MutableSharedFlow<List<CatBreed>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getBreeds(): Flow<List<CatBreed>> = _catBreeds

    override fun getBreed(id: String): Flow<CatBreed> {
        return _catBreeds.map { cats -> cats.find { it.id == id }!! }
    }

    override fun getFavouriteBreeds(): Flow<List<CatBreed>> {
        return _catBreeds.onEach { cats -> cats.filter { it.favourite } }
    }

    override suspend fun toggleBreedFavourite(id: String) {

    }

    fun setValue(catBreed: CatBreed) {
        _catBreeds.tryEmit(listOf(catBreed))
    }
}