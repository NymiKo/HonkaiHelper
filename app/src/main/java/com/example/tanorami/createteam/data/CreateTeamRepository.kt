package com.example.tanorami.createteam.data

import com.example.data.remote.util.NetworkResult
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam

interface CreateTeamRepository {
    suspend fun getHeroesList(): List<ActiveHeroInTeam>
    suspend fun saveTeam(
        idTeam: Long,
        heroesList: List<HeroBaseInfoModel>,
    ): NetworkResult<Unit>

    suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<HeroBaseInfoModel>>>
    suspend fun deleteTeam(idTeam: Long): NetworkResult<Boolean>
}