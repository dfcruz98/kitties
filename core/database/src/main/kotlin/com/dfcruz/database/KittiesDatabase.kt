package com.dfcruz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dfcruz.database.dao.BreedsDao
import com.dfcruz.database.dao.BreedsPageDao
import com.dfcruz.database.entity.BreedEntity
import com.dfcruz.database.entity.BreedPageEntity

@Database(
    entities = [BreedEntity::class, BreedPageEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class KittiesDatabase : RoomDatabase() {
    abstract fun breedsDao(): BreedsDao

    abstract fun breedsPageDao(): BreedsPageDao

}