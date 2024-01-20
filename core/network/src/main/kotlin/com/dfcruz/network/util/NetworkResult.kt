package com.dfcruz.network.util

sealed class NetworkResult<T> {
    data class Success<T>(val value: T) : NetworkResult<T>()
    data class Error<T>(val message: String? = null) : NetworkResult<T>()
    data class Exception<T>(val ex: Throwable) : NetworkResult<T>()
}