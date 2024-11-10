package com.example.tanorami.createteam.data

import com.example.tanorami.core.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.core.network.NetworkResult
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam

interface CreateTeamRepository {
    suspend fun getHeroesList(): List<ActiveHeroInTeam>
    suspend fun saveTeam(idTeam: Long, heroesList: List<HeroWithNameAvatarRarity>): NetworkResult<Unit>
    suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<HeroWithNameAvatarRarity>>>
    suspend fun deleteTeam(idTeam: Long): NetworkResult<Boolean>
}