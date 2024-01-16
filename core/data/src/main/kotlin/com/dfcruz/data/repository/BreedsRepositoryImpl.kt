package com.dfcruz.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dfcruz.data.mapping.toBreed
import com.dfcruz.data.mapping.toBreedsEntity
import com.dfcruz.data.paging.KittiesMediator
import com.dfcruz.database.KittiesDatabase
import com.dfcruz.database.entity.BreedEntity
import com.dfcruz.model.Breed
import com.dfcruz.network.service.CatsService
import com.dfcruz.network.util.RequestResult
import com.dfcruz.network.util.tryMakingRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val catsService: CatsService,
    private val database: KittiesDatabase,
) : BreedsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getBreedsPaging(): Flow<PagingData<Breed>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 100),
            remoteMediator = KittiesMediator(
                database = database,
                catsService = catsService,
            ),
            pagingSourceFactory = { database.breedsDao().pagingSource() }
        )
            .flow
            .map { paging ->
                paging.map { it.toBreed() }
            }

        return pager
    }

    override fun getBreeds(): Flow<List<Breed>> {
        return database.breedsDao().getAll()
            .onEach {
                if (it.isEmpty()) {
                    val breeds = getRemoteBreeds()
                    database.breedsDao().insertAll(breeds)
                }
            }.map { breeds ->
                breeds.map { it.toBreed() }
            }
    }

    private suspend fun getRemoteBreeds(): List<BreedEntity> {
        val response = tryMakingRequest {
            catsService.getImages(100, 1, 0)
        }

        return when (response) {
            is RequestResult.Error -> {
                listOf()
            }

            is RequestResult.Exception -> {
                listOf()
            }

            is RequestResult.Success -> {
                response.value.toBreedsEntity()
            }
        }
    }

    override fun getFavouriteBreeds(): Flow<List<Breed>> {
        return database.breedsDao().getFavourites()
            .map { breeds ->
                breeds.map { it.toBreed() }
            }
    }

    override suspend fun toggleBreedFavourite(id: String) {
        val breed = database.breedsDao().get(id)
        breed.copy(favourite = !breed.favourite).also {
            database.breedsDao().update(it)
        }
    }
}