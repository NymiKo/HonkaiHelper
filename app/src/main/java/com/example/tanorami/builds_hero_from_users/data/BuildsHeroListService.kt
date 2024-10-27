package com.example.tanorami.builds_hero_from_users.data

import com.example.tanorami.builds_hero_from_users.data.model.BuildHero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BuildsHeroListService {
    @GET("/getBuildsHeroByID.php")
    suspend fun getBuildsHeroListByIdHero(@Query("idHero") idHero: Int): Response<List<BuildHero>>

    @GET("/getBuildsHeroList.php")
    suspend fun getBuildsHeroList(): Response<List<BuildHero>>
}