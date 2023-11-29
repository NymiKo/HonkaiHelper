package com.example.honkaihelper.teams.data

import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.teams.data.model.TeamHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsListService {
    @GET("/getTeams.php")
    suspend fun getTeamsList(@Query("idHero") idHero: Int): Response<List<TeamHeroResponse>>
}