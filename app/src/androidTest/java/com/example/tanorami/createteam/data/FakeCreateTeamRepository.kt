package com.example.tanorami.createteam.data

import com.example.core.database.models.hero.HeroBaseInfoProjection
import com.example.core.network.NetworkResult
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import javax.inject.Inject

class FakeCreateTeamRepository @Inject constructor(): CreateTeamRepository {
    override suspend fun getHeroesList(): List<ActiveHeroInTeam> {
        return emptyList()
    }

    override suspend fun saveTeam(
        idTeam: Long,
        heroesList: List<HeroBaseInfoProjection>
    ): NetworkResult<Unit> {
        return NetworkResult.Success(Unit)
    }

    override suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<HeroBaseInfoProjection>>> {
        return NetworkResult.Success(Pair("", emptyList()))
    }

    override suspend fun deleteTeam(idTeam: Long): NetworkResult<Boolean> {
        return NetworkResult.Success(true)
    }
}