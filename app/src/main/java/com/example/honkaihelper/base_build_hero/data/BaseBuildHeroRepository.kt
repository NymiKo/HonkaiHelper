package com.example.honkaihelper.base_build_hero.data

import com.example.honkaihelper.base_build_hero.data.model.FullHeroInfo
import com.example.honkaihelper.data.local.models.HeroWithPathAndElement

interface BaseBuildHeroRepository {
    suspend fun getHero(idHero: Int): FullHeroInfo
}