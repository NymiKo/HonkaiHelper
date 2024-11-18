package com.example.tanorami.createteam.data

import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam

interface CreateTeamRepository {
    suspend fun getHeroesList(): List<ActiveHeroInTeam>
    suspend fun saveTeam(
        idTeam: Long,
        heroesList: List<HeroBaseInfoModel>
    ): com.example.data.remote.NetworkResult<Unit>

    suspend fun getTeam(idTeam: Long): com.example.data.remote.NetworkResult<Pair<String, List<HeroBaseInfoModel>>>
    suspend fun deleteTeam(idTeam: Long): com.example.data.remote.NetworkResult<Boolean>
}