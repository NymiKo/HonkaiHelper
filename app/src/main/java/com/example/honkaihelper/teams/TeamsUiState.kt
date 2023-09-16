package com.example.honkaihelper.teams

sealed class TeamsUiState<out T> {
    object IDLE: TeamsUiState<Nothing>()
    object LOADING: TeamsUiState<Nothing>()
    data class SUCCESS<out T>(val teamsLIst: T): TeamsUiState<T>()
    object ERROR: TeamsUiState<Nothing>()
}
