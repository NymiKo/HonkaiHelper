package com.example.honkaihelper.createteam.data

import com.example.honkaihelper.models.ActiveHeroInTeam
import com.example.honkaihelper.models.Hero

interface CreateTeamRepository {
    suspend fun getHeroesList(): List<ActiveHeroInTeam>
    suspend fun saveTeam(heroesList: List<Hero>)
}