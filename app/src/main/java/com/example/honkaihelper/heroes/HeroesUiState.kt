package com.example.honkaihelper.heroes

import com.example.honkaihelper.teams.TeamsUiState

sealed class HeroesUiState<out T> {
    object IDLE: HeroesUiState<Nothing>()
    object LOADING: HeroesUiState<Nothing>()
    data class SUCCESS<out T>(val heroesList: T): HeroesUiState<T>()
    object ERROR: HeroesUiState<Nothing>()
}
