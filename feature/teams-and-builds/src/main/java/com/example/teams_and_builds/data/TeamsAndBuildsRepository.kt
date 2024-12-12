package com.example.teams_and_builds.data

import androidx.paging.PagingData
import com.example.common.HeroBuildModel
import com.example.common.TeamHeroModel
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow

internal interface TeamsAndBuildsRepository {
    fun getTeamsListFromUsers(): Flow<PagingData<TeamHeroModel>>
    suspend fun getBuildsListFromUsers(): NetworkResult<List<HeroBuildModel>>
}