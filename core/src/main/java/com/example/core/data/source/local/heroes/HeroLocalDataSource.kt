package com.example.core.data.source.local.heroes

import com.example.core.database.models.hero.HeroFullBaseBuildRelations
import com.example.core.database.models.hero.HeroFullInfoRelations
import com.example.core.domain.repository.hero.model.HeroBaseInfoModel
import com.example.core.domain.repository.hero.model.HeroModel


interface HeroLocalDataSource {
    suspend fun getHeroesList(): List<HeroModel>
    suspend fun getHeroWithPathAndElement(idHero: Int): HeroFullInfoRelations
    suspend fun getFullBaseBuildHero(idHero: Int): HeroFullBaseBuildRelations
    suspend fun getHeroBaseInfo(idHero: Int): HeroBaseInfoModel
    suspend fun getHeroesListWithBaseInfo(): List<HeroBaseInfoModel>
    suspend fun getHeroById(idHero: Int): HeroModel
    suspend fun getHeroNameById(idHero: Int): String
    suspend fun insertHeroesList(heroesList: List<HeroModel>)
}