package com.example.tanorami.settings.presentation.models

import com.example.core.base.UiState

data class SettingsScreenUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val versionDB: String = "",
) : UiState