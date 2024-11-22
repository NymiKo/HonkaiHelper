package com.example.tanorami.teams_and_builds.data

import com.example.data.remote.util.NetworkResult
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.teams.data.model.TeamHeroes

interface TeamsAndBuildsRepository {
    suspend fun getTeamsListFromUsers(): NetworkResult<List<TeamHeroes>>
    suspend fun getBuildsListFromUsers(): NetworkResult<List<BuildHeroWithUser>>
    suspend fun getHero(idHero: Int): HeroBaseInfoModel
}