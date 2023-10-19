package com.example.honkaihelper.base_build_hero.data

import com.example.honkaihelper.base_build_hero.data.model.HeroWithPathAndElement

interface BaseBuildHeroRepository {
    suspend fun getHero(idHero: Int): HeroWithPathAndElement
}