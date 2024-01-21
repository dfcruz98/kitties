package com.dfcruz.data.fake

import com.dfcruz.network.dto.CatImageDto
import com.dfcruz.network.service.CatsService
import com.dfcruz.network.util.NetworkResult

class FakeCatsService : CatsService {

    private var returnValue: NetworkResult<List<CatImageDto>> = NetworkResult.Success(listOf())

    override suspend fun getImages(
        limit: Int,
        breed: Int,
        page: Int
    ): NetworkResult<List<CatImageDto>> {
        return returnValue
    }

    fun setReturnValue(returnValue: NetworkResult<List<CatImageDto>>) {
        this.returnValue = returnValue
    }
}