package com.example.data.remote.api.hero

import com.example.data.remote.api.hero.model.HeroDto
import retrofit2.Response
import retrofit2.http.GET

internal interface HeroApi {
    @GET("/getHeroes.php")
    suspend fun getHeroesList(): Response<List<HeroDto>>
}