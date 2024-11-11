package com.example.tanorami.heroes.data

import com.example.tanorami.heroes.data.model.Hero
import javax.inject.Inject

class FakeHeroesListRepository @Inject constructor(): HeroesListRepository {

    var state: List<Hero> = listOf()

    override suspend fun getHeroesList(): List<Hero> {
        return state
    }

}