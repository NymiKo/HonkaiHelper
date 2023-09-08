package com.example.honkaihelper.createteam.data

import com.example.honkaihelper.models.ActiveHeroInTeam

interface CreateTeamRepository {
    suspend fun getHeroesList(): List<ActiveHeroInTeam>
}