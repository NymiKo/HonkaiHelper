package com.example.tanorami.settings.presentation.models

import com.example.core.base.UiEvent

sealed interface SettingsScreenEvents: UiEvent {
    data object CheckUpdate: SettingsScreenEvents
    data object DataUpdated: SettingsScreenEvents
    data object OnSendFeedbackScreen: SettingsScreenEvents
    data object CLickDonateButton: SettingsScreenEvents
    data object OnBack: SettingsScreenEvents
}