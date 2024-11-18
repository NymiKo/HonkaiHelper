package com.example.tanorami.builds_hero_from_users.data

import com.example.core.network.NetworkResult
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser

interface BuildsHeroListRepository {
    suspend fun getHero(idHero: Int): HeroBaseInfoModel
    suspend fun getBuildsHeroListByIdHero(idHero: Int): NetworkResult<List<BuildHeroWithUser>>
}