package com.example.tanorami.teams.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.teams.data.model.TeamHero

interface TeamsListRepository {
    suspend fun getTeamsListByID(idHero: Int): NetworkResult<List<TeamHero>>
    suspend fun getTeamsList(): NetworkResult<List<TeamHero>>
    suspend fun getNameHero(idHero: Int): String
}