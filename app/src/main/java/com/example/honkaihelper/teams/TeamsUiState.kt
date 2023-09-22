package com.example.honkaihelper.teams

sealed class TeamsUiState<out T> {
    object LOADING: TeamsUiState<Nothing>()
    data class SUCCESS<out T>(val teamsList: T): TeamsUiState<T>()
    object ERROR: TeamsUiState<Nothing>()
}
