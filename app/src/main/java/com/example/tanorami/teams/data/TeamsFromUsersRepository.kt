package com.example.tanorami.teams.data

import com.example.core.network.NetworkResult
import com.example.tanorami.teams.data.model.TeamHero

interface TeamsFromUsersRepository {
    suspend fun getTeamsListByID(idHero: Int): NetworkResult<List<TeamHero>>
    suspend fun getTeamsList(): NetworkResult<List<TeamHero>>
    suspend fun getNameHero(idHero: Int): String
}