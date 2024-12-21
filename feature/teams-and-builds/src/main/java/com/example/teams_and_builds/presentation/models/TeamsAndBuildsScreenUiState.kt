package com.example.teams_and_builds.presentation.models

import androidx.paging.PagingData
import com.example.base.UiState
import com.example.base.models.TextField
import com.example.common.HeroBuildModel
import com.example.common.TeamHeroModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal data class TeamsAndBuildsScreenUiState(
    val teamsList: Flow<PagingData<TeamHeroModel>> = flowOf(PagingData.empty()),
    val buildsList: List<HeroBuildModel> = emptyList(),
    val activePageIndex: Int = 0,
    val refreshingBuildsList: Boolean = false,
    val refreshingTeamsList: Boolean = false,
    val uidTeamField: TextField = TextField(),
    val idHeroInTeamFilters: Int? = null,
    val uidBuildField: TextField = TextField(),
    val idHeroInBuildFilters: Int? = null,
) : UiState
