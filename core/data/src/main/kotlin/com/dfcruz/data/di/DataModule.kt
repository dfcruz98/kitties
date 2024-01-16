package com.dfcruz.data.di

import com.dfcruz.data.repository.BreedsRepository
import com.dfcruz.data.repository.BreedsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsBreedsRepository(breedsRepository: BreedsRepositoryImpl): BreedsRepository

}