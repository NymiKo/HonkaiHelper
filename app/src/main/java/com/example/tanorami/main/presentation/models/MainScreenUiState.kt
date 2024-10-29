package com.example.tanorami.main.presentation.models

import com.example.tanorami.base.UiState

data class MainScreenUiState(
    val userProfileAvatar: String = "",
    val remoteVersionDB: String = "",
    val message: String = "",
    val dialogUploadingDataVisibilityState: Boolean = false,
    val dialogCreateBuildOrTeamVisibilityState: Boolean = false,
) : UiState