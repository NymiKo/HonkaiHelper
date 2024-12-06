package com.example.data.source.hero_build

import com.example.data.remote.api.build.model.HeroBuildFromUserDto
import com.example.domain.util.NetworkResult

interface HeroBuildsFromUsersRemoteDataSource {
    suspend fun getHeroBuildsFromUsersByIdHero(idHero: Int): NetworkResult<List<HeroBuildFromUserDto>>
    suspend fun getBuildsListFromUsers(): NetworkResult<List<HeroBuildFromUserDto>>
}