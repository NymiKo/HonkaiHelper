package com.example.tanorami.teams.presentation.models

import com.example.core.base.UiEffect

sealed interface TeamsFromUsersScreenSideEffects : UiEffect {
    data object OnBack : TeamsFromUsersScreenSideEffects
}