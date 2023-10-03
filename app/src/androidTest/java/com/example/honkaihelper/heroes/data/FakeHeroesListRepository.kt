package com.example.honkaihelper.heroes.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.heroes.data.model.Hero
import javax.inject.Inject

class FakeHeroesListRepository @Inject constructor(): HeroesListRepository {
    override suspend fun getHeroesList(): NetworkResult<List<Hero>> {
        return NetworkResult.Success(
            listOf(
                Hero(0, "Арлан", "", false),
                Hero(1, "Блэйд", "", true),
                Hero(2, "Лука", "", false),
                Hero(3, "Кафка", "", true),
                Hero(4, "Цзинь Юань", "", true)
            )
        )
    }

    override suspend fun getAvatar(): NetworkResult<String> {
        return NetworkResult.Success("")
    }

}