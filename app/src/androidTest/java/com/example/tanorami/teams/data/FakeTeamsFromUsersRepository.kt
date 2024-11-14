package com.example.tanorami.teams.data

import com.example.core.network.NetworkResult
import com.example.tanorami.teams.data.model.TeamHero
import javax.inject.Inject

class FakeTeamsFromUsersRepository @Inject constructor() : TeamsFromUsersRepository {
    override suspend fun getTeamsListByID(idHero: Int): NetworkResult<List<TeamHero>> {
        return NetworkResult.Success(emptyList())
    }

    override suspend fun getTeamsList(): NetworkResult<List<TeamHero>> {
        return NetworkResult.Success(emptyList())
    }

    override suspend fun getNameHero(idHero: Int): String {
        return ""
    }
}