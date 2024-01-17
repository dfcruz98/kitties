package com.dfcruz.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dfcruz.database.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedsDao {

    @Query("SELECT * FROM breed WHERE id = :id")
    fun get(id: String): Flow<BreedEntity>

    @Query("SELECT * FROM breed")
    fun pagingSource(): PagingSource<Int, BreedEntity>

    @Query("SELECT * FROM breed WHERE favourite = 1")
    fun getFavourites(): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breed")
    fun getAll(): Flow<List<BreedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(breed: BreedEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breeds: List<BreedEntity>)

    @Update
    suspend fun update(breed: BreedEntity)

    @Delete
    suspend fun delete(breed: BreedEntity)

    @Query("DELETE FROM breed")
    suspend fun deleteAll()

}