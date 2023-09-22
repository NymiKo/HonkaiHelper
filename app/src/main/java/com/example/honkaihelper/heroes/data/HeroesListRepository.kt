package com.example.honkaihelper.heroes.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.models.Hero

interface HeroesListRepository {
    suspend fun getHeroesList(): NetworkResult<List<Hero>>
}