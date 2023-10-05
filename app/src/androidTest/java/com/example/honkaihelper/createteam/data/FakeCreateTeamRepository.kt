package com.example.honkaihelper.createteam.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.heroes.data.model.Hero
import javax.inject.Inject

class FakeCreateTeamRepository @Inject constructor(): CreateTeamRepository {
    override suspend fun getHeroesList(): NetworkResult<List<Hero>> {
        return NetworkResult.Success(emptyList())
    }

    override suspend fun saveTeam(heroesList: List<Hero>): NetworkResult<Unit> {
        return NetworkResult.Success(Unit)
    }
}