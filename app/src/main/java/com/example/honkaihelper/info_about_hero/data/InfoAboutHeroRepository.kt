package com.example.honkaihelper.info_about_hero.data

import com.example.honkaihelper.info_about_hero.data.model.FullHeroInfo

interface InfoAboutHeroRepository {
    suspend fun getHero(idHero: Int): FullHeroInfo
}