package com.example.honkaihelper.createteam

import com.example.honkaihelper.models.ActiveHeroInTeam

sealed class CreateTeamUIState<out T> {
    object LOADING : CreateTeamUIState<Nothing>()
    data class SUCCESS<out T>(val heroesList: List<ActiveHeroInTeam>) : CreateTeamUIState<T>()
    object ERROR : CreateTeamUIState<Nothing>()
}
