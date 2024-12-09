package com.example.data.remote.api.stat

import com.example.data.remote.api.stat.model.StatDto
import retrofit2.Response
import retrofit2.http.GET


interface StatApi {
    @GET("tables/getStats.php")
    suspend fun getStats(): Response<List<StatDto>>
}