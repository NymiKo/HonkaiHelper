package com.example.tanorami.core.data.source.local.heroes

import com.example.tanorami.core.database.models.hero.FullBaseBuildHeroEntity
import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.core.database.models.hero.HeroWithPathAndElement
import com.example.tanorami.heroes.data.model.Hero

interface HeroLocalDataSource {
    suspend fun getHeroesList(): List<Hero>
    suspend fun getHeroWithPathAndElement(idHero: Int): HeroWithPathAndElement
    suspend fun getFullBaseBuildHero(idHero: Int): FullBaseBuildHeroEntity
    suspend fun getHeroWithNameAvatarRarity(idHero: Int): HeroWithNameAvatarRarity
    suspend fun getHeroesListWithNameAvatarRarity(): List<HeroWithNameAvatarRarity>
    suspend fun getHeroById(idHero: Int): Hero
    suspend fun getHeroNameById(idHero: Int): String
    suspend fun insertHeroesList(heroesList: List<Hero>)
}