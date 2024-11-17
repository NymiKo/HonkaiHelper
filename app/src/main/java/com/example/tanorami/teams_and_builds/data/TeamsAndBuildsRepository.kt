package com.example.tanorami.teams_and_builds.data

import com.example.core.local.models.hero.HeroBaseInfoProjection
import com.example.core.network.NetworkResult
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.teams.data.model.TeamHero

interface TeamsAndBuildsRepository {
    suspend fun getTeamsListFromUsers(): NetworkResult<List<TeamHero>>
    suspend fun getBuildsListFromUsers(): NetworkResult<List<BuildHeroWithUser>>
    suspend fun getHero(idHero: Int): HeroBaseInfoProjection
}