package com.example.honkaihelper.create_build_hero.data

import com.example.honkaihelper.create_build_hero.data.model.BuildHeroFromUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateBuildHeroService {
    @POST("/saveBuildFromUser.php")
    suspend fun saveBuild(@Body build: BuildHeroFromUser): Response<Boolean>
}