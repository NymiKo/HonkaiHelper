package com.example.core.data.source.local.hero

import com.example.core.data.source.local.hero.mapper.toHeroBaseInfoModel
import com.example.core.data.source.local.hero.mapper.toHeroEntity
import com.example.core.di.IODispatcher
import com.example.data.local.dao.HeroDao
import com.example.data.local.models.hero.HeroFullBaseBuildRelations
import com.example.data.local.models.hero.HeroFullInfoRelations
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.domain.repository.hero.model.HeroModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroLocalDataSourceImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
) : HeroLocalDataSource {
    override suspend fun getHeroesList(): List<HeroModel> {
        return withContext(ioDispatcher) {
            heroDao.getHeroesList().map { heroEntity -> heroEntity.toHeroModel() }
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

    override suspend fun getHeroBaseInfo(idHero: Int): HeroBaseInfoModel {
        return withContext(ioDispatcher) {
            heroDao.getHeroWithNameAvatarRarity(idHero).toHeroBaseInfoModel()
        }
    }

    override suspend fun getHeroesListWithBaseInfo(): List<HeroBaseInfoModel> {
        return withContext(ioDispatcher) {
            heroDao.getHeroesListWithNameAvatarRarity().map { heroBaseInfoProjection ->
                heroBaseInfoProjection.toHeroBaseInfoModel()
            }
        }
    }

    override suspend fun getHeroById(idHero: Int): HeroModel {
        return withContext(ioDispatcher) {
            heroDao.getHeroById(idHero).toHeroModel()
        }
    }

    override suspend fun getHeroNameById(idHero: Int): String {
        return withContext(ioDispatcher) {
            heroDao.getHeroNameById(idHero)
        }
    }

    override suspend fun insertHeroesList(heroesList: List<HeroModel>) {
        withContext(ioDispatcher) {
            heroDao.insertHeroesList(heroesList.map { hero -> hero.toHeroEntity() })
        }
    }
}