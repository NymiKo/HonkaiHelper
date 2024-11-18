package com.example.tanorami.teams.data

import com.example.tanorami.teams.data.model.TeamHero

interface TeamsFromUsersRepository {
    suspend fun getTeamsListByID(idHero: Int): com.example.data.remote.NetworkResult<List<TeamHero>>
    suspend fun getTeamsList(): com.example.data.remote.NetworkResult<List<TeamHero>>
    suspend fun getNameHero(idHero: Int): String
}