package com.example.tanorami.heroes.data

import com.example.tanorami.core.network.NetworkResult
import com.example.tanorami.heroes.data.model.Hero
import javax.inject.Inject

class FakeHeroesListRepository @Inject constructor(): HeroesListRepository {

    var state: List<Hero> = listOf(
//        Hero(0, "Арлан", "", "", false, 0, 0),
//        Hero(1, "Блэйд", "", "", true, 0, 0),
//        Hero(2, "Лука", "", "", false, 0, 0),
//        Hero(3, "Кафка", "", "", true, 0, 0),
//        Hero(4, "Цзинь Юань", "", "", true, 0, 0)
    )

    override suspend fun getHeroesList(): List<Hero> {
        return state
    }

    override suspend fun getAvatar(): NetworkResult<String> {
        return NetworkResult.Success("")
    }

}