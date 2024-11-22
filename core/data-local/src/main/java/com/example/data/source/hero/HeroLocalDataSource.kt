package com.example.data.source.hero

import com.example.data.db.entity.HeroEntity
import com.example.data.db.models.hero.HeroBaseInfoProjection
import com.example.data.db.models.hero.HeroFullBaseBuildRelations
import com.example.data.db.models.hero.HeroFullInfoRelations


interface HeroLocalDataSource {
    suspend fun getHeroesList(): List<HeroEntity>
    suspend fun getHeroWithPathAndElement(idHero: Int): HeroFullInfoRelations
    suspend fun getFullBaseBuildHero(idHero: Int): HeroFullBaseBuildRelations
    suspend fun getHeroBaseInfo(idHero: Int): HeroBaseInfoProjection
    suspend fun getHeroesListWithBaseInfo(): List<HeroBaseInfoProjection>
    suspend fun getHeroById(idHero: Int): HeroEntity
    suspend fun getHeroNameById(idHero: Int): String
    suspend fun insertHeroesList(heroesList: List<HeroEntity>)
}