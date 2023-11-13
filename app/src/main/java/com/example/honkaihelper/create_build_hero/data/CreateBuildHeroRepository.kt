package com.example.honkaihelper.create_build_hero.data

import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity

interface CreateBuildHeroRepository {
    suspend fun getHero(idHero: Int): HeroWithNameAvatarRarity
}