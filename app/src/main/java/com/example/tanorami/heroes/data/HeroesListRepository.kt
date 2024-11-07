package com.example.tanorami.heroes.data

import com.example.tanorami.heroes.data.model.Hero

interface HeroesListRepository {
    suspend fun getHeroesList(): List<Hero>
}