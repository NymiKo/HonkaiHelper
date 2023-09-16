package com.example.honkaihelper.teams

import com.example.honkaihelper.models.TeamHero

data class TeamsUiState(
    val isLoading: Boolean = false,
    val teamsList: List<TeamHero> = listOf()
)
