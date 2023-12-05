package com.example.honkaihelper.settings

sealed class SettingsUIState {
    object LOADING: SettingsUIState()
    object LATEST_VERSION: SettingsUIState()
    object NEW_VERSION: SettingsUIState()
    data class ERROR(val message: Int): SettingsUIState()
}