package com.example.tanorami.main.data

import com.example.tanorami.main.data.models.NewDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface MainScreenService {
    @GET("/updatingData.php")
    suspend fun getRemoteVersionDB(): Response<NewDataResponse>

    @POST("/avatar.php")
    suspend fun getAvatar(): Response<String>
}