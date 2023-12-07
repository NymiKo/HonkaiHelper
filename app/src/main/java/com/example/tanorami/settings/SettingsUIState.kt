package com.example.tanorami.settings

sealed class SettingsUIState {
    object LOADING: SettingsUIState()
    object DATA_UPDATED: SettingsUIState()
    object LATEST_VERSION: SettingsUIState()
    data class NEW_VERSION(val versionDb: String): SettingsUIState()
    data class ERROR(val message: Int): SettingsUIState()
}