package com.example.tanorami.teams.presentation.models

sealed interface TeamsFromUsersScreenSideEffects : com.example.base.UiEffect {
    data object OnBack : TeamsFromUsersScreenSideEffects
}