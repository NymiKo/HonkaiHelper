package com.example.tanorami.heroes.presentation

sealed class HeroesUiState<out T> {
    object LOADING : HeroesUiState<Nothing>()
    data class SUCCESS<out T>(val heroesList: T) : HeroesUiState<T>()
    object ERROR : HeroesUiState<Nothing>()
}
