package com.dfcruz.data.repository

import androidx.paging.PagingData
import com.dfcruz.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {

    fun getBreedsPaging(): Flow<PagingData<Breed>>

    fun getBreeds(): Flow<List<Breed>>

    fun getBreed(id: String): Flow<Breed>

    fun getFavouriteBreeds(): Flow<List<Breed>>

    suspend fun toggleBreedFavourite(id: String)

}