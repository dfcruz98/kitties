package com.dfcruz.data.testhelper

import com.dfcruz.database.dao.CatBreedsDao
import com.dfcruz.database.entity.CatBreedEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TestCatsBreedsDao : CatBreedsDao {

    private var returnValue = mutableListOf<CatBreedEntity>()
    override fun get(id: String): Flow<CatBreedEntity> {
        return flowOf(returnValue.first { it.id == id })
    }

    override fun getFavourites(): Flow<List<CatBreedEntity>> {
        return flowOf(returnValue.filter { it.favourite })
    }

    override fun getAll(): Flow<List<CatBreedEntity>> {
        return flowOf(returnValue)
    }

    override suspend fun insert(catBreeds: CatBreedEntity) {
        returnValue.add(catBreeds)
    }

    override suspend fun insertAll(catBreeds: List<CatBreedEntity>) {
        returnValue.addAll(catBreeds)
    }

    override suspend fun update(catBreeds: CatBreedEntity) {
        returnValue.removeIf { it.id == catBreeds.id }
        returnValue.add(catBreeds)
    }

    override suspend fun delete(catBreeds: CatBreedEntity) {
        returnValue.remove(catBreeds)
    }

    override suspend fun deleteAll() {
        returnValue.clear()
    }
}