package com.example.tanorami.builds_hero_from_users.data

import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.core.network.NetworkResult

interface BuildsHeroListRepository {
    suspend fun getHero(idHero: Int): HeroWithNameAvatarRarity
    suspend fun getBuildsHeroListByIdHero(idHero: Int): NetworkResult<List<BuildHeroWithUser>>
}