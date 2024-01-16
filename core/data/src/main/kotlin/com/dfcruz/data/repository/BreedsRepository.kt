package com.dfcruz.data.repository

import com.dfcruz.database.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {

    fun getBreeds(): Flow<List<BreedEntity>>
}