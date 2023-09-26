package com.example.honkaihelper.createteam

import com.example.honkaihelper.createteam.data.model.ActiveHeroInTeam

sealed class CreateTeamUIState<out T> {
    object LOADING : CreateTeamUIState<Nothing>()
    data class SUCCESS(val heroesList: List<ActiveHeroInTeam>) : CreateTeamUIState<Nothing>()
    data class ERROR(val message: Int) : CreateTeamUIState<Nothing>()
    object LOADING_TEAM_CREATION : CreateTeamUIState<Nothing>()
    data class ERROR_TEAM_CREATION(val message: Int) : CreateTeamUIState<Nothing>()
    object SUCCESS_TEAM_CREATION : CreateTeamUIState<Nothing>()
}
