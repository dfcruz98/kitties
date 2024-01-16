package com.dfcruz.network.util

sealed class RequestResult<T> {
    data class Success<T>(val value: T) : RequestResult<T>()
    data class Error<T>(val message: String? = null) : RequestResult<T>()
    data class Exception<T>(val ex: java.lang.Exception) : RequestResult<T>()
}