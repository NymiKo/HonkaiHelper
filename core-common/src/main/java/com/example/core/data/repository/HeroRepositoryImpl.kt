package com.example.core.data.repository

import com.example.core.data.source.local.hero.HeroLocalDataSource
import com.example.core.data.source.local.hero.mapper.toHeroFullBaseBuildModel
import com.example.core.data.source.local.hero.mapper.toHeroFullInfoModel
import com.example.domain.repository.hero.HeroRepository
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.domain.repository.hero.model.HeroFullBaseBuildModel
import com.example.domain.repository.hero.model.HeroFullInfoModel
import com.example.domain.repository.hero.model.HeroModel
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val heroLocalDataSource: HeroLocalDataSource
) : HeroRepository {
    override suspend fun getHeroesList(): List<HeroModel> {
        return heroLocalDataSource.getHeroesList()
    }

    override suspend fun getHeroWithPathAndElement(idHero: Int): HeroFullInfoModel {
        return heroLocalDataSource.getHeroWithPathAndElement(idHero).toHeroFullInfoModel()
    }

    override suspend fun getFullBaseBuildHero(idHero: Int): HeroFullBaseBuildModel {
        return heroLocalDataSource.getFullBaseBuildHero(idHero).toHeroFullBaseBuildModel()
    }

    override suspend fun getHeroBaseInfo(idHero: Int): HeroBaseInfoModel {
        return heroLocalDataSource.getHeroBaseInfo(idHero)
    }

    override suspend fun getHeroesListWithBaseInfo(): List<HeroBaseInfoModel> {
        return heroLocalDataSource.getHeroesListWithBaseInfo().sortedBy { it.name }
    }

    override suspend fun getHeroById(idHero: Int): HeroModel {
        return heroLocalDataSource.getHeroById(idHero)
    }

    override suspend fun getHeroNameById(idHero: Int): String {
        return getHeroNameById(idHero)
    }

    override suspend fun insertHeroesList(heroesList: List<HeroModel>) {
        heroLocalDataSource.insertHeroesList(heroesList)
    }
}