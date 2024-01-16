package com.dfcruz.data.repository

import com.dfcruz.data.mapping.toBreedsEntity
import com.dfcruz.database.dao.BreedsDao
import com.dfcruz.database.entity.BreedEntity
import com.dfcruz.network.service.CatsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val catsService: CatsService,
    private val breedsDao: BreedsDao,
) : BreedsRepository {

    override fun getBreeds(): Flow<List<BreedEntity>> = flow {
        val response = catsService.getImages(1)
        val breeds = response.body()?.toBreedsEntity()
        breedsDao.insertAll(breeds ?: listOf())
        emit(breedsDao.getAll().toList().flatten())
    }

}