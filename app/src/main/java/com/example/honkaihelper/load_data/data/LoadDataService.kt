package com.example.honkaihelper.load_data.data

import com.example.honkaihelper.base_build_hero.data.model.Ability
import com.example.honkaihelper.base_build_hero.data.model.Eidolon
import com.example.honkaihelper.base_build_hero.data.model.Element
import com.example.honkaihelper.base_build_hero.data.model.Path
import com.example.honkaihelper.heroes.data.model.Hero
import retrofit2.Response
import retrofit2.http.GET

interface LoadDataService {
    @GET("/getHeroes.php")
    suspend fun getHeroesList(): Response<List<Hero>>

    @GET("/getPaths.php")
    suspend fun getPathsList(): Response<List<Path>>

    @GET("/getElements.php")
    suspend fun getElementsList(): Response<List<Element>>

    @GET("/getAbilities.php")
    suspend fun getAbilitiesList(): Response<List<Ability>>

    @GET("/getEidolons.php")
    suspend fun getEidolonsList(): Response<List<Eidolon>>
}