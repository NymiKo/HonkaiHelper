package com.example.tanorami.teams_and_builds.data

import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.core.network.NetworkResult
import com.example.tanorami.teams.data.model.TeamHero

interface TeamsAndBuildsRepository {
    suspend fun getTeamsListFromUsers(): NetworkResult<List<TeamHero>>
    suspend fun getBuildsListFromUsers(): NetworkResult<List<BuildHeroWithUser>>
    suspend fun getHero(idHero: Int): HeroWithNameAvatarRarity
}