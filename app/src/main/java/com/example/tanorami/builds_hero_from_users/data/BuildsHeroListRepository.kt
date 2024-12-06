package com.example.tanorami.builds_hero_from_users.data

import com.example.common.HeroBuildModel
import com.example.domain.util.NetworkResult

interface BuildsHeroListRepository {
    suspend fun getHero(idHero: Int): com.example.common.HeroBaseInfoModel
    suspend fun getBuildsHeroListByIdHero(idHero: Int): NetworkResult<List<HeroBuildModel>>
}