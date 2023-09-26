package com.example.honkaihelper.createteam.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CreateTeamService {
    @GET("/saveTeam.php")
    suspend fun saveTeam(@Query("heroes_list[]") heroesList: List<Int>): Response<Unit>
}