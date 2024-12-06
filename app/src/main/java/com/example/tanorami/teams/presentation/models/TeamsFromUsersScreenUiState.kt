package com.example.tanorami.teams.presentation.models

import com.example.common.TeamHeroModel

data class TeamsFromUsersScreenUiState(
    val isError: Boolean = false,
    val tokenUser: String = "",
    val idHero: Int? = null,
    val nameHero: String = "",
    val teamsList: List<TeamHeroModel> = emptyList(),
    val refreshingTeamsList: Boolean = false,
) : com.example.base.UiState
