package com.example.tanorami.core.data

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val code: Int) : NetworkResult<Nothing>()
}