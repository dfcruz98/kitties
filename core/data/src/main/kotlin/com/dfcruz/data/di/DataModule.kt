package com.dfcruz.data.di

import com.dfcruz.data.repository.CatBreedsRepository
import com.dfcruz.data.repository.CatsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsBreedsRepository(breedsRepository: CatsRepositoryImpl): CatBreedsRepository

}