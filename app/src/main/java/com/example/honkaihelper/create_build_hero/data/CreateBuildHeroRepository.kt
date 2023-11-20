package com.example.honkaihelper.create_build_hero.data

import com.example.honkaihelper.create_build_hero.data.model.BuildHeroFromUser
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.model.Hero

interface CreateBuildHeroRepository {
    suspend fun getHero(idHero: Int): Hero
    suspend fun saveBuild(buildHeroFromUser: BuildHeroFromUser)
}