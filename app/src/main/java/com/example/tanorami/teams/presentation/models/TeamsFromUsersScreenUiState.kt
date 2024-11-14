package com.example.tanorami.teams.presentation.models

import com.example.core.base.UiState
import com.example.tanorami.teams.data.model.TeamHero

data class TeamsFromUsersScreenUiState(
    val isError: Boolean = false,
    val tokenUser: String = "",
    val idHero: Int? = null,
    val nameHero: String = "",
    val teamsList: List<TeamHero> = emptyList(),
    val refreshingTeamsList: Boolean = false,
) : UiState
