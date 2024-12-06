package com.example.data.remote.api.build

import com.example.data.remote.api.build.model.HeroBuildFromUserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroBuildsFromUsersApi {
    @GET("/getBuildsHeroByID.php")
    suspend fun getHeroBuildsFromUsersByIdHero(@Query("idHero") idHero: Int): Response<List<HeroBuildFromUserDto>>

    @GET("/getBuildsHeroList.php")
    suspend fun getHeroBuildsFromUsersList(): Response<List<HeroBuildFromUserDto>>
}