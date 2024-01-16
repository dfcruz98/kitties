package com.dfcruz.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dfcruz.data.mapping.toBreed
import com.dfcruz.data.paging.KittiesMediator
import com.dfcruz.database.KittiesDatabase
import com.dfcruz.model.Breed
import com.dfcruz.network.service.CatsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val catsService: CatsService,
    private val database: KittiesDatabase,
) : BreedsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getBreeds(): Flow<PagingData<Breed>> {
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

}