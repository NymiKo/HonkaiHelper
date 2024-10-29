package com.example.tanorami.teams.data

import com.example.tanorami.teams.data.model.TeamHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsFromUsersService {
    @GET("/getTeamsByID.php")
    suspend fun getTeamsListByID(@Query("idHero") idHero: Int): Response<List<TeamHeroResponse>>

    @GET("/getTeamsList.php")
    suspend fun getTeamsList(): Response<List<TeamHeroResponse>>
}