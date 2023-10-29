package com.example.honkaihelper.base_build_hero.data

import com.example.honkaihelper.base_build_hero.data.model.FullHeroInfo

interface InfoAboutHeroRepository {
    suspend fun getHero(idHero: Int): FullHeroInfo
}