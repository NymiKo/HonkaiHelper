package com.example.tanorami.navigation.main

import retrofit2.Response
import retrofit2.http.GET

interface MainScreenService {
    @GET("/versionDB.php")
    suspend fun getRemoteVersionDB(): Response<String>
}