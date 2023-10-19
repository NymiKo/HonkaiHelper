package com.example.honkaihelper.activity.data

import retrofit2.Response
import retrofit2.http.GET

interface MainService {
    @GET("/versionDB.php")
    suspend fun getVersionDB(): Response<String>
}