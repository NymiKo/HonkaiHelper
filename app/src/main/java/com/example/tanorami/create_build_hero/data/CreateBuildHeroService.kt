package com.example.tanorami.create_build_hero.data

import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CreateBuildHeroService {
    @POST("/saveBuildFromUser.php")
    suspend fun saveBuild(@Body build: BuildHeroFromUser): Response<Boolean>

    @GET("/getHeroBuildByID.php")
    suspend fun getBuild(@Query("idBuild") idBuild: Long): Response<BuildHeroFromUser>

    @POST("/updateBuildHero.php")
    suspend fun updateBuild(@Body build: BuildHeroFromUser): Response<Boolean>

    @GET("/deleteBuildHero.php")
    suspend fun deleteBuild(@Query("idBuild") idBuild: Long): Response<Boolean>
}