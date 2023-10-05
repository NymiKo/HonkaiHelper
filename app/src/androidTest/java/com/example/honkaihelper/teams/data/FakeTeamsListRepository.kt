package com.example.honkaihelper.teams.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.teams.data.TeamsListRepository
import com.example.honkaihelper.teams.data.model.TeamHero
import javax.inject.Inject

class FakeTeamsListRepository @Inject constructor(): TeamsListRepository {
    override suspend fun getTeamsList(idHero: Int): NetworkResult<List<TeamHero>> {
        return NetworkResult.Success(emptyList())
    }
}