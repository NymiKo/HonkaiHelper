package com.example.domain.repository.hero

import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.domain.repository.hero.model.HeroFullBaseBuildModel
import com.example.domain.repository.hero.model.HeroFullInfoModel
import com.example.domain.repository.hero.model.HeroModel


interface HeroRepository {
    suspend fun getHeroesList(): List<HeroModel>
    suspend fun getHeroWithPathAndElement(idHero: Int): HeroFullInfoModel
    suspend fun getFullBaseBuildHero(idHero: Int): HeroFullBaseBuildModel
    suspend fun getHeroBaseInfo(idHero: Int): HeroBaseInfoModel
    suspend fun getHeroesListWithBaseInfo(): List<HeroBaseInfoModel>
    suspend fun getHeroById(idHero: Int): HeroModel
    suspend fun getHeroNameById(idHero: Int): String
    suspend fun insertHeroesList(heroesList: List<HeroModel>)
}