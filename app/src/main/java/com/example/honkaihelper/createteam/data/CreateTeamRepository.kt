package com.example.honkaihelper.createteam.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.heroes.data.model.Hero

interface CreateTeamRepository {
    suspend fun getHeroesList(): NetworkResult<List<Hero>>
    suspend fun saveTeam(heroesList: List<Hero>): NetworkResult<Unit>
}