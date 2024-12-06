package com.example.tanorami.teams.data

import com.example.common.TeamHeroModel
import com.example.domain.util.NetworkResult

interface TeamsFromUsersRepository {
    suspend fun getTeamsListByID(idHero: Int): NetworkResult<List<TeamHeroModel>>
    suspend fun getTeamsList(): NetworkResult<List<TeamHeroModel>>
    suspend fun getNameHero(idHero: Int): String
}