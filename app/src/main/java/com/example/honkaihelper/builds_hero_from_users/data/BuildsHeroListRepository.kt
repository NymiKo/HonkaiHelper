package com.example.honkaihelper.builds_hero_from_users.data

import com.example.honkaihelper.builds_hero_from_users.data.model.FullBuildHeroFromUser
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity

interface BuildsHeroListRepository {
    suspend fun getHero(idHero: Int): HeroWithNameAvatarRarity
    suspend fun getBuildsHeroList(idHero: Int): NetworkResult<List<FullBuildHeroFromUser>>
}