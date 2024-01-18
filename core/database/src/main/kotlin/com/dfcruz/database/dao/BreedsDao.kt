package com.dfcruz.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dfcruz.database.entity.CatBreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedsDao {

    @Query("SELECT * FROM cat_breed WHERE id = :id")
    fun get(id: String): Flow<CatBreedEntity>

    @Query("SELECT * FROM cat_breed")
    fun pagingSource(): PagingSource<Int, CatBreedEntity>

    @Query("SELECT * FROM cat_breed WHERE favourite = 1")
    fun getFavourites(): Flow<List<CatBreedEntity>>

    @Query("SELECT * FROM cat_breed")
    fun getAll(): Flow<List<CatBreedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catBreeds: CatBreedEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(catBreeds: List<CatBreedEntity>)

    @Update
    suspend fun update(catBreeds: CatBreedEntity)

    @Delete
    suspend fun delete(catBreeds: CatBreedEntity)

    @Query("DELETE FROM cat_breed")
    suspend fun deleteAll()

}