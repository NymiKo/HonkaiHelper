package com.example.tanorami.teams_and_builds.presentation.models

import com.example.tanorami.auth.login.presentation.models.TextField
import com.example.tanorami.base.UiState
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.teams.data.model.TeamHero

data class TeamsAndBuildsScreenUiState(
    val teamsList: List<TeamHero> = emptyList(),
    val buildsList: List<BuildHeroWithUser> = emptyList(),
    val searchText: TextField = TextField(),
    val activePageIndex: Int = 0,
    val refreshingBuildsList: Boolean = false,
    val refreshingTeamsList: Boolean = false,
) : UiState
