package com.dfcruz.data.repository

import android.util.Log
import com.dfcruz.data.mapping.toBreedsEntity
import com.dfcruz.database.dao.BreedsDao
import com.dfcruz.database.entity.BreedEntity
import com.dfcruz.network.service.CatsService
import com.dfcruz.network.util.RequestResult
import com.dfcruz.network.util.tryMakingRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val catsService: CatsService,
    private val breedsDao: BreedsDao,
) : BreedsRepository {

    override fun getBreeds(): Flow<List<BreedEntity>> = flow {
        when (val result = tryMakingRequest { catsService.getImages(1) }) {
            is RequestResult.Error -> {
                Log.e("BreedsRepositoryImpl", result.message ?: "Something went wrong!")

            }

            is RequestResult.Exception -> {
                Log.e("BreedsRepositoryImpl", "Error fetching breeds", result.ex)
            }

            is RequestResult.Success -> {
                val breeds = result.value.toBreedsEntity()
                breedsDao.insertAll(breeds)
                emit(breedsDao.getAll().toList().flatten())
            }
        }
    }

}