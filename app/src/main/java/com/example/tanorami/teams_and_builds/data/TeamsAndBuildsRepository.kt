package com.example.tanorami.teams_and_builds.data

import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.teams.data.model.TeamHero

interface TeamsAndBuildsRepository {
    suspend fun getTeamsListFromUsers(): com.example.data.remote.NetworkResult<List<TeamHero>>
    suspend fun getBuildsListFromUsers(): com.example.data.remote.NetworkResult<List<BuildHeroWithUser>>
    suspend fun getHero(idHero: Int): HeroBaseInfoModel
}