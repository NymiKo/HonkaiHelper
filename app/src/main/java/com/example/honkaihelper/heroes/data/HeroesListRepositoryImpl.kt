package com.example.honkaihelper.heroes.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesListRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroesListService: HeroesListService
) : HeroesListRepository {

    override suspend fun getHeroesList(): NetworkResult<List<Hero>> {
        return withContext(ioDispatcher) {
            handleApi {
                heroesListService.getHeroesList()
            }
        }
    }
}