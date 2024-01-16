package com.dfcruz.data.repository

import androidx.paging.PagingData
import com.dfcruz.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {

    fun getBreeds(): Flow<PagingData<Breed>>
}