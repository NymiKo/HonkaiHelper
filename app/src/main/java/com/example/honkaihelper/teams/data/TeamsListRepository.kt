package com.example.honkaihelper.teams.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.models.TeamHero

interface TeamsListRepository {
    suspend fun getTeamsList(idHero: Int): NetworkResult<List<TeamHero>>
}