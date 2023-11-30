package com.example.honkaihelper.createteam.data

import com.example.honkaihelper.createteam.data.model.ActiveHeroInTeam
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.model.Hero

interface CreateTeamRepository {
    suspend fun getHeroesList(): List<ActiveHeroInTeam>
    suspend fun saveTeam(idTeam: Int, heroesList: List<HeroWithNameAvatarRarity>): NetworkResult<Unit>
    suspend fun getTeam(idTeam: Int): NetworkResult<List<HeroWithNameAvatarRarity>>
}