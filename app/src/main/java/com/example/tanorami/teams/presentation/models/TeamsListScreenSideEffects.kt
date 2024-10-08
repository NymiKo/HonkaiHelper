package com.example.tanorami.teams.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface TeamsListScreenSideEffects : UiEffect {
    data object OnCreateTeamScreen: TeamsListScreenSideEffects
    data object OnBack: TeamsListScreenSideEffects
}