package com.example.tanorami.heroes.data

import com.example.tanorami.core.database.dao.HeroDao
import com.example.tanorami.core.di.IODispatcher
import com.example.tanorami.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesListRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
) : HeroesListRepository {
    override suspend fun getHeroesList(): List<Hero> {
        return withContext(ioDispatcher) {
            heroDao.getHeroes().map { it.toHero() }.sortedBy { it.name }
        }
    }
}