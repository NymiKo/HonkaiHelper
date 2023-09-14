package com.example.honkaihelper.data

suspend fun <T> handleApi(apiCall: suspend() -> T): Result<T> {
    return try {
        Result.success(apiCall.invoke())
    } catch (throwable: Throwable) {
        Result.failure(throwable)
    }
}