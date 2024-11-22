package com.example.tanorami.teams.data

import com.example.data.remote.util.NetworkResult
import com.example.tanorami.teams.data.model.TeamHeroes
import javax.inject.Inject

class FakeTeamsFromUsersRepository @Inject constructor() : TeamsFromUsersRepository {
    override suspend fun getTeamsListByID(idHero: Int): NetworkResult<List<TeamHeroes>> {
        return NetworkResult.Success(emptyList())
    }

    override suspend fun getTeamsList(): NetworkResult<List<TeamHeroes>> {
        return NetworkResult.Success(emptyList())
    }

    override suspend fun getNameHero(idHero: Int): String {
        return ""
    }
}