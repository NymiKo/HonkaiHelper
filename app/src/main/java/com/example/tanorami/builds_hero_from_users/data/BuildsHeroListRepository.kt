package com.example.tanorami.builds_hero_from_users.data

import com.example.core.database.models.hero.HeroBaseInfoProjection
import com.example.core.network.NetworkResult
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser

interface BuildsHeroListRepository {
    suspend fun getHero(idHero: Int): HeroBaseInfoProjection
    suspend fun getBuildsHeroListByIdHero(idHero: Int): NetworkResult<List<BuildHeroWithUser>>
}