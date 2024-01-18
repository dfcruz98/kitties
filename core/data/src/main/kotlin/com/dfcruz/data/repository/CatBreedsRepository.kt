package com.dfcruz.data.repository

import androidx.paging.PagingData
import com.dfcruz.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatBreedsRepository {

    fun getBreedsPaging(): Flow<PagingData<CatBreed>>

    fun getBreeds(): Flow<List<CatBreed>>

    fun getBreed(id: String): Flow<CatBreed>

    fun getFavouriteBreeds(): Flow<List<CatBreed>>

    suspend fun toggleBreedFavourite(id: String)

}