package com.example.tanorami.createteam.data

import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity
import javax.inject.Inject

class FakeCreateTeamRepository @Inject constructor(): CreateTeamRepository {
    override suspend fun getHeroesList(): List<ActiveHeroInTeam> {
        return emptyList()
    }

    override suspend fun saveTeam(idTeam: Int, heroesList: List<HeroWithNameAvatarRarity>): NetworkResult<Unit> {
        return NetworkResult.Success(Unit)
    }

    override suspend fun getTeam(idTeam: Int): NetworkResult<List<HeroWithNameAvatarRarity>> {
        return NetworkResult.Success(emptyList())
    }

    override suspend fun deleteTeam(idTeam: Int): NetworkResult<Boolean> {
        return NetworkResult.Success(true)
    }
}