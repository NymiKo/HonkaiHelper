package com.example.tanorami.createteam.data

import com.example.core.database.models.hero.HeroBaseInfoProjection
import com.example.core.network.NetworkResult
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam

interface CreateTeamRepository {
    suspend fun getHeroesList(): List<ActiveHeroInTeam>
    suspend fun saveTeam(
        idTeam: Long,
        heroesList: List<HeroBaseInfoProjection>
    ): NetworkResult<Unit>

    suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<HeroBaseInfoProjection>>>
    suspend fun deleteTeam(idTeam: Long): NetworkResult<Boolean>
}