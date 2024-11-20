package com.example.tanorami.settings.presentation.models

data class SettingsScreenUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val versionDB: String = "",
) : com.example.base.UiState