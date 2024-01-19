package com.dfcruz.data.repository

import com.dfcruz.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatBreedsRepository {

    fun getBreeds(): Flow<List<CatBreed>>

    fun getBreed(id: String): Flow<CatBreed>

    fun getFavouriteBreeds(): Flow<List<CatBreed>>

    suspend fun toggleBreedFavourite(id: String)

}