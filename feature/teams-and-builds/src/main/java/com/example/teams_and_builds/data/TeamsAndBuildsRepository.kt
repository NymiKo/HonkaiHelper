package com.example.teams_and_builds.data

import com.example.common.HeroBuildModel
import com.example.common.TeamHeroModel
import com.example.domain.util.NetworkResult

internal interface TeamsAndBuildsRepository {
    suspend fun getTeamsListFromUsers(): NetworkResult<List<TeamHeroModel>>
    suspend fun getBuildsListFromUsers(): NetworkResult<List<HeroBuildModel>>
}