package com.example.tanorami.createteam.data

import com.example.domain.util.NetworkResult
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam

interface CreateTeamRepository {
    suspend fun getHeroesList(): List<ActiveHeroInTeam>
    suspend fun saveTeam(
        idTeam: Long,
        heroesList: List<com.example.common.HeroBaseInfoModel>,
    ): NetworkResult<Unit>

    suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<com.example.common.HeroBaseInfoModel>>>
    suspend fun deleteTeam(idTeam: Long): NetworkResult<Boolean>
}