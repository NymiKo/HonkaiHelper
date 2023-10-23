package com.example.honkaihelper.heroes.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.image_loader.ImageLoader
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesListRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
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