package com.dfcruz.network.util

import retrofit2.Response

internal inline fun <V, T> T.tryMakingRequest(
    request: T.() -> Response<V>,
): NetworkResult<V> {
    val response: Response<V>

    try {
        response = request()
    } catch (ex: Exception) {
        return NetworkResult.Exception(ex)
    }

    return if (response.isSuccessful) {
        val body = response.body()
        if (body == null) {
            NetworkResult.Error()
        } else {
            NetworkResult.Success(body)
        }
    } else {
        NetworkResult.Error()
    }
}