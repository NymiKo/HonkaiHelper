package com.example.core.data.repository

import com.example.core.data.source.local.heroes.HeroLocalDataSource
import com.example.core.database.models.hero.HeroFullBaseBuildRelations
import com.example.core.database.models.hero.HeroFullInfoRelations
import com.example.core.domain.repository.hero.HeroRepository
import com.example.core.domain.repository.hero.model.HeroBaseInfoModel
import com.example.core.domain.repository.hero.model.HeroModel
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val heroLocalDataSource: HeroLocalDataSource
) : HeroRepository {
    override suspend fun getHeroesList(): List<HeroModel> {
        return heroLocalDataSource.getHeroesList()
    }

    override suspend fun getHeroWithPathAndElement(idHero: Int): HeroFullInfoRelations {
        return heroLocalDataSource.getHeroWithPathAndElement(idHero)
    }

    override suspend fun getFullBaseBuildHero(idHero: Int): HeroFullBaseBuildRelations {
        return heroLocalDataSource.getFullBaseBuildHero(idHero)
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