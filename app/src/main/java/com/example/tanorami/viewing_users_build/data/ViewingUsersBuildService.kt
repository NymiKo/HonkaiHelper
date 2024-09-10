package com.example.tanorami.viewing_users_build.data

import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ViewingUsersBuildService {
    @GET("/getHeroBuildByID.php")
    suspend fun getHeroBuildByID(@Query("idBuild") idBuild: Long): Response<BuildHeroFromUser>

    @GET("/getHeroBuildByUID.php")
    suspend fun getHeroBuildByUID(@Query("uid") uid: String): Response<BuildHeroFromUser>
}