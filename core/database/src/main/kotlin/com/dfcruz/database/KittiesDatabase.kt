package com.dfcruz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dfcruz.database.dao.CatBreedsDao
import com.dfcruz.database.entity.CatBreedEntity

@Database(
    entities = [CatBreedEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class KittiesDatabase : RoomDatabase() {
    abstract fun catBreedsDao(): CatBreedsDao

}