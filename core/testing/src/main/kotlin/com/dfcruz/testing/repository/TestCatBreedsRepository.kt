package com.dfcruz.testing.repository

import com.dfcruz.data.repository.CatBreedsRepository
import com.dfcruz.model.CatBreed
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class TestCatBreedsRepository : CatBreedsRepository {

    private val _catBreeds =
        MutableSharedFlow<List<CatBreed>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getBreeds(): Flow<List<CatBreed>> = _catBreeds

    override fun getBreed(id: String): Flow<CatBreed> {
        return _catBreeds.map { cats -> cats.find { it.id == id }!! }
    }

    override fun getFavouriteBreeds(): Flow<List<CatBreed>> {
        return _catBreeds.map { cats -> cats.filter { it.favourite } }
    }

    override suspend fun toggleBreedFavourite(id: String) {

    }

    fun emit(catBreeds: List<CatBreed>) {
        _catBreeds.tryEmit(catBreeds)
    }
}