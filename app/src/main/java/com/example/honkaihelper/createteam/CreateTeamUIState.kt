package com.example.honkaihelper.createteam

import com.example.honkaihelper.models.ActiveHeroInTeam

sealed class CreateTeamUIState<out T> {
    object LOADING : CreateTeamUIState<Nothing>()
    data class SUCCESS(val heroesList: List<ActiveHeroInTeam>) : CreateTeamUIState<Nothing>()
    data class ERROR(val message: Int) : CreateTeamUIState<Nothing>()
    object LOADING_COMMAND_CREATION : CreateTeamUIState<Nothing>()
    data class ERROR_COMMAND_CREATION(val message: Int) : CreateTeamUIState<Nothing>()
    object SUCCESS_COMMAND_CREATION : CreateTeamUIState<Nothing>()
}
