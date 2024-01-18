package com.dfcruz.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dfcruz.data.mapping.toBreedsEntity
import com.dfcruz.database.KittiesDatabase
import com.dfcruz.database.dao.BreedsPageDao
import com.dfcruz.database.entity.CatBreedEntity
import com.dfcruz.database.entity.BreedPageEntity
import com.dfcruz.network.service.CatsService
import com.dfcruz.network.util.RequestResult
import com.dfcruz.network.util.tryMakingRequest
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class KittiesMediator @Inject constructor(
    private val database: KittiesDatabase,
    private val catsService: CatsService,
) : RemoteMediator<Int, CatBreedEntity>() {

    private val breedsPageDao: BreedsPageDao = database.catBreedsPageDao()
    private val breedsDao = database.catBreedsDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatBreedEntity>
    ): MediatorResult {

        Log.d("KittiesMediator", "loadType=$loadType")

        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: 0
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.previousPage ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                prevPage
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                nextPage
            }
        }

        Log.d("KittiesMediator", "currentPage=$currentPage")

        return when (val response =
            tryMakingRequest { catsService.getImages(30, 1, currentPage) }
        ) {
            is RequestResult.Error -> {
                Log.d("KittiesMediator", "Error")
                return MediatorResult.Error(Throwable())
            }

            is RequestResult.Exception -> {
                Log.d("KittiesMediator", response.ex.message ?: "Something went wrong!")
                return MediatorResult.Error(response.ex)
            }

            is RequestResult.Success -> {

                val endOfPaginationReached = response.value.isEmpty()

                val prevPage = if (currentPage == 0) null else currentPage - 1
                val nextPage = if (endOfPaginationReached) null else currentPage + 1

                Log.d("KittiesMediator", "endOfPaginationReached=$endOfPaginationReached, prevPage=$prevPage, nextPage=$nextPage, response=${response.value.size}")

                val breeds = mutableListOf<CatBreedEntity>()
                val pages = mutableListOf<BreedPageEntity>()

                response.value.forEach { image ->
                    val imageBreeds = image.toBreedsEntity()
                    breeds.addAll(imageBreeds)
                    imageBreeds.map {
                        BreedPageEntity(
                            breedId = it.id,
                            previousPage = prevPage,
                            nextPage = nextPage
                        )
                    }.also {
                        pages.addAll(it)
                    }

                }

                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        breedsDao.deleteAll()
                        breedsPageDao.deleteAll()
                    }

                    breedsDao.insertAll(breeds)
                    breedsPageDao.insert(pages)
                }

                MediatorResult.Success(
                    endOfPaginationReached = endOfPaginationReached
                )
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CatBreedEntity>
    ): BreedPageEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                breedsPageDao.get(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CatBreedEntity>
    ): BreedPageEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { challenge ->
                breedsPageDao.get(id = challenge.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CatBreedEntity>
    ): BreedPageEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { challenge ->
                breedsPageDao.get(id = challenge.id)
            }
    }

}