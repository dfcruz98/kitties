package com.dfcruz.data.repository

import com.dfcruz.data.mapping.toBreed
import com.dfcruz.data.mapping.toBreedsEntity
import com.dfcruz.database.dao.CatBreedsDao
import com.dfcruz.model.CatBreed
import com.dfcruz.network.service.CatsService
import com.dfcruz.network.util.NetworkResult
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
    private val catBreedsDao: CatBreedsDao,
) : CatBreedsRepository {

    override fun getBreeds(): Flow<List<CatBreed>> {
        return catBreedsDao.getAll()
            .onEach {
                if (it.isEmpty()) {
                    catsService.getImages(100, REQUEST_WITH_BREED, 0).also { response ->
                        val breeds = when (response) {
                            is NetworkResult.Error,
                            is NetworkResult.Exception -> listOf()

                            is NetworkResult.Success -> response.value.toBreedsEntity()
                        }
                        catBreedsDao.insertAll(breeds)
                    }
                }
            }.map { breeds ->
                breeds.map { it.toBreed() }
            }
    }

    override fun getBreed(id: String): Flow<CatBreed> {
        return catBreedsDao.get(id).map { it.toBreed() }
    }

    override fun getFavouriteBreeds(): Flow<List<CatBreed>> {
        return catBreedsDao.getFavourites()
            .map { breeds ->
                breeds.map { it.toBreed() }
            }
    }

    override suspend fun toggleBreedFavourite(id: String) {
        val catBreed = catBreedsDao.get(id).first()
        catBreed.copy(favourite = !catBreed.favourite).also {
            catBreedsDao.update(it)
        }
    }
}