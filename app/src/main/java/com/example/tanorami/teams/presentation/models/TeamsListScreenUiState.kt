package com.example.tanorami.teams.presentation.models

import com.example.tanorami.base.UiState
import com.example.tanorami.teams.data.model.TeamHero

data class TeamsListScreenUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val tokenUser: String = "",
    val uid: String = "",
    val nameHero: String = "",
    val teamsList: List<TeamHero> = emptyList()
) : UiState
