package com.example.honkaihelper.createteam

sealed class CreateTeamUIState {
    object CREATING_TEAM: CreateTeamUIState()
    data class ERROR_TEAM_CREATION(val message: Int): CreateTeamUIState()
    object SUCCESS_TEAM_CREATION: CreateTeamUIState()
    object SUCCESS_TEAM_DELETION: CreateTeamUIState()
    object LOADING_TEAM_CREATION: CreateTeamUIState()
}