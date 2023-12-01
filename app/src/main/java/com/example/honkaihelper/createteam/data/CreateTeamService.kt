package com.example.honkaihelper.createteam.data

import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.teams.data.model.TeamHeroResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CreateTeamService {
    @POST("/saveTeam.php")
    suspend fun saveTeam(@Body heroesList: List<Int>): Response<Unit>

    @GET("/getTeam.php")
    suspend fun getTeam(@Query("idTeam") idTeam: Int): Response<TeamHeroResponse>

    @GET("/updateTeam.php")
    suspend fun updateTeam(@Query("idTeam") idTeam: Int, @Query("heroesList[]") heroesList: List<Int>): Response<Unit>

    @GET("/deleteTeam.php")
    suspend fun deleteTeam(@Query("idTeam") idTeam: Int): Response<Boolean>
}