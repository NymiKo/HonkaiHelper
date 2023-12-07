package com.example.tanorami.base_build_hero.data

import com.example.tanorami.base_build_hero.data.model.FullBaseBuildHero

interface BaseBuildHeroRepository {
    suspend fun getBaseBuildHero(idHero: Int): FullBaseBuildHero
}