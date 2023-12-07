package com.example.tanorami.heroes.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.heroes.data.model.Hero

interface HeroesListRepository {
    suspend fun getHeroesList(): List<Hero>
    suspend fun getAvatar(): NetworkResult<String>
}