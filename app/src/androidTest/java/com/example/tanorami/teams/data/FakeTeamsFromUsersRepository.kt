package com.example.tanorami.teams.data

import com.example.tanorami.teams.data.model.TeamHero
import javax.inject.Inject

class FakeTeamsFromUsersRepository @Inject constructor() : TeamsFromUsersRepository {
    override suspend fun getTeamsListByID(idHero: Int): com.example.data.remote.NetworkResult<List<TeamHero>> {
        return com.example.data.remote.NetworkResult.Success(emptyList())
    }

    override suspend fun getTeamsList(): com.example.data.remote.NetworkResult<List<TeamHero>> {
        return com.example.data.remote.NetworkResult.Success(emptyList())
    }

    override suspend fun getNameHero(idHero: Int): String {
        return ""
    }
}