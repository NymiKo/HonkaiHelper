package com.example.tanorami.teams_and_builds.presentation.models

import com.example.core.base.UiState
import com.example.core.base.models.TextField
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.teams.data.model.TeamHeroes

data class TeamsAndBuildsScreenUiState(
    val teamsList: List<TeamHeroes> = emptyList(),
    val buildsList: List<BuildHeroWithUser> = emptyList(),
    val searchText: TextField = TextField(),
    val activePageIndex: Int = 0,
    val refreshingBuildsList: Boolean = false,
    val refreshingTeamsList: Boolean = false,
) : UiState
