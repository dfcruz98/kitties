package com.dfcruz.network.util

import retrofit2.Response

inline fun <V, T> T.tryMakingRequest(
    request: T.() -> Response<V>,
): RequestResult<V> {
    val response: Response<V>

    try {
        response = request()
    } catch (ex: Exception) {
        return RequestResult.Exception(ex)
    }

    return if (response.isSuccessful) {
        val body = response.body()
        if (body == null) {
            RequestResult.Error()
        } else {
            RequestResult.Success(body)
        }
    } else {
        RequestResult.Error()
    }
}