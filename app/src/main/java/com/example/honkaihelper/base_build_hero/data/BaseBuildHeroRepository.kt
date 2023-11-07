package com.example.honkaihelper.base_build_hero.data

import com.example.honkaihelper.base_build_hero.data.model.FullBaseBuildHero

interface BaseBuildHeroRepository {
    suspend fun getBaseBuildHero(idHero: Int): FullBaseBuildHero
}