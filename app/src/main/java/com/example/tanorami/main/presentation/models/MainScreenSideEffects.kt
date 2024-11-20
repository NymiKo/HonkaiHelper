package com.example.tanorami.main.presentation.models

sealed interface MainScreenSideEffects : com.example.base.UiEffect {
    class OnLoadDataScreen(val remoteVersionDB: String) : MainScreenSideEffects
    data object CreateBuildForHeroScreen : MainScreenSideEffects
    data object OnCreateTeamScreen : MainScreenSideEffects
    class ShowToast(val message: Int) : MainScreenSideEffects
}