package com.dfcruz.network.di

import com.dfcruz.network.service.CatsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://api.thecatapi.com/v1/"
private const val HTTP_REQUEST_READ_TIMEOUT = 30L
private const val HTTP_REQUEST_WRITE_TIMEOUT = 30L
private const val HTTP_REQUEST_CONNECTION_TIMEOUT = 10L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val builder = OkHttpClient
            .Builder()
            .connectTimeout(HTTP_REQUEST_READ_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_REQUEST_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(HTTP_REQUEST_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideSpaceXService(retrofit: Retrofit): CatsService {
        return retrofit.create(CatsService::class.java)
    }
}