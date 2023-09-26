package com.example.honkaihelper.teams.data

import com.example.honkaihelper.teams.model.TeamHero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsListService {
    @GET("/getTeams.php")
    suspend fun getTeamsList(@Query("id") idHero: Int): Response<List<TeamHero>>
}