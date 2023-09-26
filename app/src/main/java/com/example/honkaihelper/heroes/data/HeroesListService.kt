package com.example.honkaihelper.heroes.data

import com.example.honkaihelper.heroes.data.model.Hero
import retrofit2.Response
import retrofit2.http.GET

interface HeroesListService {

    @GET("/getHeroes.php")
    suspend fun getHeroesList(): Response<List<Hero>>
}