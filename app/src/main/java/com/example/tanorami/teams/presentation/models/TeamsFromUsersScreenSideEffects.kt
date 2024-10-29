package com.example.tanorami.teams.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface TeamsFromUsersScreenSideEffects : UiEffect {
    data object OnCreateTeamScreen : TeamsFromUsersScreenSideEffects
    data object OnBack : TeamsFromUsersScreenSideEffects
}