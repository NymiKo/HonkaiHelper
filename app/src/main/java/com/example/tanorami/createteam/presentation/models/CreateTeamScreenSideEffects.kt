package com.example.tanorami.createteam.presentation.models

import com.example.base.UiEffect

sealed interface CreateTeamScreenSideEffects : UiEffect {
    data object TeamDeleted : CreateTeamScreenSideEffects
    data object TeamSaved : CreateTeamScreenSideEffects
    data class ShowToastError(val message: Int) : CreateTeamScreenSideEffects
    data object OnBack : CreateTeamScreenSideEffects
}