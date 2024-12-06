package com.example.teams_and_builds.presentation.models

import com.example.base.UiState
import com.example.base.models.TextField
import com.example.common.TeamHeroModel

internal data class TeamsAndBuildsScreenUiState(
    val teamsList: List<TeamHeroModel> = emptyList(),
    val buildsList: List<com.example.common.HeroBuildModel> = emptyList(),
    val searchText: TextField = TextField(),
    val activePageIndex: Int = 0,
    val refreshingBuildsList: Boolean = false,
    val refreshingTeamsList: Boolean = false,
) : UiState
