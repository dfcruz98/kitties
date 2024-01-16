package com.dfcruz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dfcruz.database.dao.BreedsDao
import com.dfcruz.database.entity.BreedEntity

@Database(
    entities = [BreedEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class KittiesDatabase : RoomDatabase() {
    abstract fun imagesDao(): BreedsDao

}