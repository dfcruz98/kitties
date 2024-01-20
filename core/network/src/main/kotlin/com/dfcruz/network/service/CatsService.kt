package com.dfcruz.network.service

import com.dfcruz.network.dto.CatImageDto
import com.dfcruz.network.util.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsService {

    @GET("images/search")
    suspend fun getImages(
        @Query("limit") limit: Int,
        @Query("has_breeds") breed: Int,
        @Query("page") page: Int,
    ): NetworkResult<List<CatImageDto>>
}