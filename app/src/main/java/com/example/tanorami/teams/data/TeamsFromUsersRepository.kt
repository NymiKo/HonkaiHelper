package com.example.tanorami.teams.data

import com.example.data.remote.util.NetworkResult
import com.example.tanorami.teams.data.model.TeamHeroes

interface TeamsFromUsersRepository {
    suspend fun getTeamsListByID(idHero: Int): NetworkResult<List<TeamHeroes>>
    suspend fun getTeamsList(): NetworkResult<List<TeamHeroes>>
    suspend fun getNameHero(idHero: Int): String
}