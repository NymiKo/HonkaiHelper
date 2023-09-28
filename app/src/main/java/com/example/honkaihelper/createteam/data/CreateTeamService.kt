package com.example.honkaihelper.createteam.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CreateTeamService {
    @POST("/saveTeam.php")
    suspend fun saveTeam(@Body heroesList: List<Int>): Response<Unit>
}