package com.example.tanorami.createteam.data

import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import javax.inject.Inject

class FakeCreateTeamRepository @Inject constructor(): CreateTeamRepository {
    override suspend fun getHeroesList(): List<ActiveHeroInTeam> {
        return emptyList()
    }

    override suspend fun saveTeam(
        idTeam: Long,
        heroesList: List<HeroBaseInfoModel>
    ): com.example.data.remote.NetworkResult<Unit> {
        return com.example.data.remote.NetworkResult.Success(Unit)
    }

    override suspend fun getTeam(idTeam: Long): com.example.data.remote.NetworkResult<Pair<String, List<HeroBaseInfoModel>>> {
        return com.example.data.remote.NetworkResult.Success(Pair("", emptyList()))
    }

    override suspend fun deleteTeam(idTeam: Long): com.example.data.remote.NetworkResult<Boolean> {
        return com.example.data.remote.NetworkResult.Success(true)
    }
}