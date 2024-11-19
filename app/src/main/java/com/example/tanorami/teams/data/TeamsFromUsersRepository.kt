package com.example.tanorami.teams.data

import com.example.tanorami.teams.data.model.TeamHeroes

interface TeamsFromUsersRepository {
    suspend fun getTeamsListByID(idHero: Int): com.example.data.remote.NetworkResult<List<TeamHeroes>>
    suspend fun getTeamsList(): com.example.data.remote.NetworkResult<List<TeamHeroes>>
    suspend fun getNameHero(idHero: Int): String
}