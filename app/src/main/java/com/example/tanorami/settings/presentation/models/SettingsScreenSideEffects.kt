package com.example.tanorami.settings.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface SettingsScreenSideEffects: UiEffect {
    class ShowToast(val message: Int): SettingsScreenSideEffects
    data object CLickDonateButton: SettingsScreenSideEffects
    data object OnLoadDataScreen: SettingsScreenSideEffects
    data object OnSendFeedbackScreen: SettingsScreenSideEffects
    data object OnBack: SettingsScreenSideEffects
}