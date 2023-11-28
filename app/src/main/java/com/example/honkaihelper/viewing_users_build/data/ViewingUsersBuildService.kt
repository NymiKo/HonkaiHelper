package com.example.honkaihelper.viewing_users_build.data

import com.example.honkaihelper.create_build_hero.data.model.BuildHeroFromUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ViewingUsersBuildService {
    @GET("/getHeroBuild.php")
    suspend fun getHeroBuild(@Query("idBuild") idBuild: Int): Response<BuildHeroFromUser>
}