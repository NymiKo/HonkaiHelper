package com.example.tanorami.teams.presentation.models

import com.example.tanorami.base.UiState
import com.example.tanorami.teams.data.model.TeamHero

data class TeamsFromUsersScreenUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val tokenUser: String = "",
    val nameHero: String = "",
    val teamsList: List<TeamHero> = emptyList()
) : UiState
