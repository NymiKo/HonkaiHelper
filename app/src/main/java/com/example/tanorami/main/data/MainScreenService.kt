package com.example.tanorami.main.data

import retrofit2.Response
import retrofit2.http.GET

interface MainScreenService {
    @GET("/versionDB.php")
    suspend fun getRemoteVersionDB(): Response<String>
}