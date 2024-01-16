package com.dfcruz.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dfcruz.database.entity.BreedPageEntity

@Dao
interface BreedsPageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pages: List<BreedPageEntity>)

    @Query("SELECT * FROM breed_page WHERE breedId = :id")
    suspend fun get(id: String): BreedPageEntity

    @Delete
    suspend fun delete(page: BreedPageEntity)

    @Query("DELETE FROM breed_page")
    suspend fun deleteAll()

}