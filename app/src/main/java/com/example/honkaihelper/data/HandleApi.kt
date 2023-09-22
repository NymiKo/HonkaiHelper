package com.example.honkaihelper.data

import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> handleApi(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.success(apiCall.invoke())
    } catch (e: UnknownHostException) {
        Result.failure(e)
    } catch (e: HttpException) {
        Result.failure(e)
    }
}