package com.example.honkaihelper.heroes.data

import com.example.honkaihelper.models.Hero
import retrofit2.http.GET

interface HeroesListService {

    @GET("/getHeroes.php")
    suspend fun getHeroesList(): List<Hero>
}