package com.example.data.remote.api.team

import com.example.data.remote.api.team.model.TeamFromUserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsFromUsersApi {
    @GET("/getTeamsByID.php")
    suspend fun getTeamsFromUsersListByIdHero(@Query("idHero") idHero: Int): Response<List<TeamFromUserDto>>

    @GET("/getTeamsListPaging.php")
    suspend fun getTeamsFromUsersList(@Query("page") page: Int): List<TeamFromUserDto>
}