package com.example.honkaihelper.base_build_hero.data

import com.example.honkaihelper.base_build_hero.data.model.HeroWithPathAndElement
import com.example.honkaihelper.data.local.dao.HeroDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BaseBuildHeroRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao
): BaseBuildHeroRepository {
    override suspend fun getHero(idHero: Int): HeroWithPathAndElement {
        return withContext(ioDispatcher) {
            heroDao.getHeroWithPathAndElement(idHero)
        }
    }
}