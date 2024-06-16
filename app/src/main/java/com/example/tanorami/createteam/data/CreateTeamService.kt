package com.example.tanorami.createteam.data

import com.example.tanorami.teams.data.model.TeamHeroResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CreateTeamService {
    @POST("/saveTeam.php")
    suspend fun saveTeam(@Body heroesList: List<Int>): Response<Unit>

    @GET("/getTeam.php")
    suspend fun getTeam(@Query("idTeam") idTeam: Long): Response<TeamHeroResponse>

    @GET("/updateTeam.php")
    suspend fun updateTeam(@Query("idTeam") idTeam: Long, @Query("heroesList[]") heroesList: List<Int>): Response<Unit>

    @GET("/deleteTeam.php")
    suspend fun deleteTeam(@Query("idTeam") idTeam: Long): Response<Boolean>
}