package com.dfcruz.network.service

import com.dfcruz.network.dto.CatImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsService {

    @GET("images/search")
    suspend fun getImages(
        @Query("limit") limit: Int,
        @Query("has_breeds") breed: Int,
        @Query("page") page: Int,
    ): Response<List<CatImageDto>>
}