package com.dfcruz.network.service

import com.dfcruz.network.dto.CatImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsService {

    @GET("images/")
    suspend fun getImages(
        @Query("has_breeds") breed: Int,
    ): Response<List<CatImageDto>>
}