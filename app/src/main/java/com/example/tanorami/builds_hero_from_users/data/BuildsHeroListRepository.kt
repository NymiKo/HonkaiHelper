package com.example.tanorami.builds_hero_from_users.data

import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity

interface BuildsHeroListRepository {
    suspend fun getHero(idHero: Int): HeroWithNameAvatarRarity
    suspend fun getBuildsHeroListByIdHero(idHero: Int): NetworkResult<List<BuildHeroWithUser>>
}