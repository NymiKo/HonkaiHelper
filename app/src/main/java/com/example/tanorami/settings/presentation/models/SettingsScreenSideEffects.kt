package com.example.tanorami.settings.presentation.models

sealed interface SettingsScreenSideEffects : com.example.base.UiEffect {
    class ShowToast(val message: Int): SettingsScreenSideEffects
    data object CLickDonateButton: SettingsScreenSideEffects
    class OnLoadDataScreen(val versionDB: String): SettingsScreenSideEffects
    data object OnSendFeedbackScreen: SettingsScreenSideEffects
    data object OnBack: SettingsScreenSideEffects
}