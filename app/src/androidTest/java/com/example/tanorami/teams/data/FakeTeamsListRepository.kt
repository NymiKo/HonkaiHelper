package com.example.tanorami.teams.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.teams.data.model.TeamHero
import javax.inject.Inject

class FakeTeamsListRepository @Inject constructor(): TeamsListRepository {
    override suspend fun getTeamsList(idHero: Int): NetworkResult<List<TeamHero>> {
        return NetworkResult.Success(emptyList())
    }

    override suspend fun getNameHero(idHero: Int): String {
        return ""
    }
}