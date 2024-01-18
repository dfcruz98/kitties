package com.dfcruz.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dfcruz.data.mapping.toBreed
import com.dfcruz.data.mapping.toBreedsEntity
import com.dfcruz.data.paging.PagingMediator
import com.dfcruz.database.KittiesDatabase
import com.dfcruz.model.CatBreed
import com.dfcruz.network.service.CatsService
import com.dfcruz.network.util.RequestResult
import com.dfcruz.network.util.tryMakingRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// Flag indicating that the request of the images should only return images that have an associated race
// Documentation: https://developers.thecatapi.com/view-account/ylX4blBYT9FaoVd6OhvR?report=bOoHBz-8t
const val REQUEST_WITH_BREED = 1

class CatsRepositoryImpl @Inject constructor(
    private val catsService: CatsService,
    private val database: KittiesDatabase,
) : CatBreedsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getBreedsPaging(): Flow<PagingData<CatBreed>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 100),
            remoteMediator = PagingMediator(
                database = database,
                catsService = catsService,
            ),
            pagingSourceFactory = { database.catBreedsDao().pagingSource() }
        )
            .flow
            .map { paging ->
                paging.map { it.toBreed() }
            }

        return pager
    }

    override fun getBreeds(): Flow<List<CatBreed>> {
        return database.catBreedsDao().getAll()
            .onEach {
                if (it.isEmpty()) {
                    val response = tryMakingRequest {
                        catsService.getImages(100, REQUEST_WITH_BREED, 0)
                    }

                    val breeds = when (response) {
                        is RequestResult.Error,
                        is RequestResult.Exception -> listOf()

                        is RequestResult.Success -> response.value.toBreedsEntity()
                    }
                    database.catBreedsDao().insertAll(breeds)
                }
            }.map { breeds ->
                breeds.map { it.toBreed() }
            }
    }

    override fun getBreed(id: String): Flow<CatBreed> {
        return database.catBreedsDao().get(id).map { it.toBreed() }
    }

    override fun getFavouriteBreeds(): Flow<List<CatBreed>> {
        return database.catBreedsDao().getFavourites()
            .map { breeds ->
                breeds.map { it.toBreed() }
            }
    }

    override suspend fun toggleBreedFavourite(id: String) {
        val catBreed = database.catBreedsDao().get(id).first()
        catBreed.copy(favourite = !catBreed.favourite).also {
            database.catBreedsDao().update(it)
        }
    }
}