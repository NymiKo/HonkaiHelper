package com.example.honkaihelper.heroes.data

import com.example.honkaihelper.heroes.data.model.Hero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface HeroesListService {

    @GET("/getHeroes.php")
    suspend fun getHeroesList(): Response<List<Hero>>

    @POST("/avatar.php")
    suspend fun getAvatar(): Response<String>
}