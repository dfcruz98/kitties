package com.dfcruz.database.di

import android.content.Context
import androidx.room.Room
import com.dfcruz.database.KittiesDatabase
import com.dfcruz.database.dao.BreedsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "kitties-database"

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideKittiesDatabase(
        @ApplicationContext context: Context
    ): KittiesDatabase = Room.databaseBuilder(
        context,
        KittiesDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideBreedsDao(
        database: KittiesDatabase
    ): BreedsDao {
        return database.imagesDao()
    }

}