package com.dfcruz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dfcruz.database.dao.BreedsDao
import com.dfcruz.database.dao.BreedsPageDao
import com.dfcruz.database.entity.CatBreedEntity
import com.dfcruz.database.entity.BreedPageEntity

@Database(
    entities = [CatBreedEntity::class, BreedPageEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class KittiesDatabase : RoomDatabase() {
    abstract fun catBreedsDao(): BreedsDao

    abstract fun catBreedsPageDao(): BreedsPageDao

}