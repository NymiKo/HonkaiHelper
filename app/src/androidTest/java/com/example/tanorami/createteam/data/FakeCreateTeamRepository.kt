package com.example.tanorami.createteam.data

import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.core.network.NetworkResult
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import javax.inject.Inject

class FakeCreateTeamRepository @Inject constructor(): CreateTeamRepository {
    override suspend fun getHeroesList(): List<ActiveHeroInTeam> {
        return emptyList()
    }

    override suspend fun saveTeam(
        idTeam: Long,
        heroesList: List<HeroWithNameAvatarRarity>
    ): NetworkResult<Unit> {
        return NetworkResult.Success(Unit)
    }

    override suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<HeroWithNameAvatarRarity>>> {
        return NetworkResult.Success(Pair("", emptyList()))
    }

    override suspend fun deleteTeam(idTeam: Long): NetworkResult<Boolean> {
        return NetworkResult.Success(true)
    }
}