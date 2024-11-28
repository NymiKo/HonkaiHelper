package com.example.data.source.hero

import com.example.data.db.dao.HeroDao
import com.example.data.db.entity.HeroEntity
import com.example.data.db.models.hero.HeroBaseInfoProjection
import com.example.data.db.models.hero.HeroFullBaseBuildRelations
import com.example.data.db.models.hero.HeroFullInfoRelations
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroLocalDataSourceImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
) : HeroLocalDataSource {
    override suspend fun getHeroesList(): List<HeroEntity> {
        return withContext(ioDispatcher) {
            heroDao.getHeroesList()
        }
    }

    override suspend fun getHeroWithPathAndElement(idHero: Int): HeroFullInfoRelations {
        return withContext(ioDispatcher) {
            heroDao.getHeroWithPathAndElement(idHero)
        }
    }

    override suspend fun getFullBaseBuildHero(idHero: Int): HeroFullBaseBuildRelations {
        return withContext(ioDispatcher) {
            heroDao.getFullBaseBuildHero(idHero)
        }
    }

    override suspend fun getHeroBaseInfo(idHero: Int): HeroBaseInfoProjection {
        return withContext(ioDispatcher) {
            heroDao.getHeroWithNameAvatarRarity(idHero)
        }
    }

    override suspend fun getHeroesListWithBaseInfo(): List<HeroBaseInfoProjection> {
        return withContext(ioDispatcher) {
            heroDao.getHeroesListWithNameAvatarRarity()
        }
    }

    override suspend fun getHeroById(idHero: Int): HeroEntity {
        return withContext(ioDispatcher) {
            heroDao.getHeroById(idHero)
        }
    }

    override suspend fun getHeroNameById(idHero: Int): String {
        return withContext(ioDispatcher) {
            heroDao.getHeroNameById(idHero)
        }
    }

    override suspend fun insertHeroesList(heroesList: List<HeroEntity>) {
        withContext(ioDispatcher) {
            heroDao.insertHeroesList(heroesList)
        }
    }
}