package com.example.data.remote

import com.example.domain.data_store.AppDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor(
    private val appDataStore: AppDataStore,
) : Interceptor {
    override fun intercept(chain: Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = runBlocking {
            appDataStore.tokenUser.first()
        }

        requestBuilder.addHeader("Authorization", "Bearer $token")

        return chain.proceed(requestBuilder.build())
    }
}