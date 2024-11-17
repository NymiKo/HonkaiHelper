package com.example.core.network

import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> handleApi(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = apiCall()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(response.code())
        }
    } catch (e: UnknownHostException) {
        NetworkResult.Error(105)
    } catch (e: SocketTimeoutException) {
        NetworkResult.Error(106)
    }
}