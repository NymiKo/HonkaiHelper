package com.example.tanorami.settings.presentation.models

data class SettingsScreenUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val versionDB: String = "",
    val showSnowfallAnimation: Boolean = false,
    val countSnowflakes: Float = 80F,
) : com.example.base.UiState