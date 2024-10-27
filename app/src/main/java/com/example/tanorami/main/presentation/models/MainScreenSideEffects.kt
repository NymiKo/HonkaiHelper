package com.example.tanorami.main.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface MainScreenSideEffects : UiEffect {
    class OnLoadDataScreen(val remoteVersionDB: String) : MainScreenSideEffects
}