package com.example.tanorami.heroes.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.data.local.dao.HeroDao
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesListRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val heroesListService: HeroesListService
) : HeroesListRepository {

    override suspend fun getHeroesList(): List<Hero> {
        return withContext(ioDispatcher) {
            heroDao.getHeroes().map { it.toHero() }.sortedBy { it.name }
        }
    }

    override suspend fun getAvatar(): NetworkResult<String> {
        return withContext(ioDispatcher) {
            handleApi {
                heroesListService.getAvatar()
            }
        }
    }
}