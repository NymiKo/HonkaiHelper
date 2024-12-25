package com.example.tanorami.main.presentation.models

data class MainScreenUiState(
    val userProfileAvatar: String = "",
    val remoteVersionDB: String = "",
    val message: String = "",
    val importantMessageModel: ImportantMessageModel = ImportantMessageModel(),
    val dialogUploadingDataVisibilityState: Boolean = false,
    val dialogCreateBuildOrTeamVisibilityState: Boolean = false,
    val dialogImportantMessageVisibilityState: Boolean = false,
) : com.example.base.UiState