package com.example.tanorami.settings.presentation.models

sealed interface SettingsScreenEvents : com.example.base.UiEvent {
    data object CheckUpdate: SettingsScreenEvents
    data object DataUpdated: SettingsScreenEvents
    data object OnSendFeedbackScreen: SettingsScreenEvents
    data object CLickDonateButton: SettingsScreenEvents
    data object ChangeStateSnowfallAnimation : SettingsScreenEvents
    class ChangeCountSnowflakesAnimation(val count: Float) : SettingsScreenEvents
    data object OnBack: SettingsScreenEvents
}