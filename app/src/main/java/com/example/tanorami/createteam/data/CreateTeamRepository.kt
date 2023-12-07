package com.example.tanorami.createteam.data

import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity

interface CreateTeamRepository {
    suspend fun getHeroesList(): List<ActiveHeroInTeam>
    suspend fun saveTeam(idTeam: Int, heroesList: List<HeroWithNameAvatarRarity>): NetworkResult<Unit>
    suspend fun getTeam(idTeam: Int): NetworkResult<List<HeroWithNameAvatarRarity>>
    suspend fun deleteTeam(idTeam: Int): NetworkResult<Boolean>
}