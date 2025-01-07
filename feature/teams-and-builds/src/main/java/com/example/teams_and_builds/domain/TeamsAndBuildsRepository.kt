package com.example.teams_and_builds.domain

import androidx.paging.PagingData
import com.example.common.HeroBuildModel
import com.example.common.TeamHeroModel
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow

internal interface TeamsAndBuildsRepository {
    fun getTeamsListFromUsers(uid: String, idHero: Int?): Flow<PagingData<TeamHeroModel>>
    suspend fun getBuildsListFromUsers(
        uid: String,
        idHero: Int?,
    ): NetworkResult<List<HeroBuildModel>>
}