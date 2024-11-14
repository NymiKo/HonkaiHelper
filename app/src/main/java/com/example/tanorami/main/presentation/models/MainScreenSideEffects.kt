package com.example.tanorami.main.presentation.models

import com.example.core.base.UiEffect

sealed interface MainScreenSideEffects : UiEffect {
    class OnLoadDataScreen(val remoteVersionDB: String) : MainScreenSideEffects
    data object CreateBuildForHeroScreen : MainScreenSideEffects
    data object OnCreateTeamScreen : MainScreenSideEffects
    class ShowToast(val message: Int) : MainScreenSideEffects
}