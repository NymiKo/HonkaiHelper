package com.example.data.repository

import com.example.common.HeroBaseInfoModel
import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.data.source.hero.mapper.toHeroEntity
import com.example.data.source.hero.mapper.toHeroFullBaseBuildModel
import com.example.data.source.hero.mapper.toHeroFullInfoModel
import com.example.data.source.hero.mapper.toHeroModel
import com.example.domain.di.DispatcherIo
import com.example.domain.repository.hero.HeroRepository
import com.example.domain.repository.hero.model.HeroFullBaseBuildModel
import com.example.domain.repository.hero.model.HeroFullInfoModel
import com.example.domain.repository.hero.model.HeroModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


internal class HeroRepositoryImpl @Inject constructor(
    private val heroLocalDataSource: HeroLocalDataSource,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : HeroRepository {
    override suspend fun getHeroesList(): List<HeroModel> {
        return withContext(ioDispatcher) {
            heroLocalDataSource.getHeroesList().map { it.toHeroModel() }
        }
    }

    override suspend fun getHeroWithPathAndElement(idHero: Int): HeroFullInfoModel {
        return withContext(ioDispatcher) {
            heroLocalDataSource.getHeroWithPathAndElement(idHero).toHeroFullInfoModel()
        }
    }

    override suspend fun getFullBaseBuildHero(idHero: Int): HeroFullBaseBuildModel {
        return withContext(ioDispatcher) {
            heroLocalDataSource.getFullBaseBuildHero(idHero).toHeroFullBaseBuildModel()
        }
    }

    override suspend fun getHeroBaseInfo(idHero: Int): HeroBaseInfoModel {
        return withContext(ioDispatcher) {
            heroLocalDataSource.getHeroBaseInfo(idHero).toHeroBaseInfoModel()
        }
    }

    override suspend fun getHeroesListWithBaseInfo(): List<HeroBaseInfoModel> {
        return withContext(ioDispatcher) {
            heroLocalDataSource.getHeroesListWithBaseInfo().sortedBy { it.name }
                .map { it.toHeroBaseInfoModel() }
        }
    }

    override suspend fun getHeroById(idHero: Int): HeroModel {
        return withContext(ioDispatcher) {
            heroLocalDataSource.getHeroById(idHero).toHeroModel()
        }
    }

    override suspend fun getHeroNameById(idHero: Int): String {
        return getHeroNameById(idHero)
    }

    override suspend fun insertHeroesList(heroesList: List<HeroModel>) {
        withContext(ioDispatcher) {
            heroLocalDataSource.insertHeroesList(heroesList.map { it.toHeroEntity() })
        }
    }
}