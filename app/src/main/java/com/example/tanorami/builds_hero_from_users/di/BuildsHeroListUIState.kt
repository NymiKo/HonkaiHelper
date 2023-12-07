package com.example.tanorami.builds_hero_from_users.di

sealed class BuildsHeroListUIState<out T> {
    object LOADING: BuildsHeroListUIState<Nothing>()
    data class SUCCESS<out T>(val buildsHeroList: List<T>): BuildsHeroListUIState<T>()
    object EMPTY: BuildsHeroListUIState<Nothing>()
    object ERROR: BuildsHeroListUIState<Nothing>()
}