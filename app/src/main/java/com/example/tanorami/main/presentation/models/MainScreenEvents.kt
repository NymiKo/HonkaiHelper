package com.example.tanorami.main.presentation.models

sealed interface MainScreenEvents : com.example.base.UiEvent {
    data object DialogUploadingDataButtonOkClick : MainScreenEvents
    class ChangeVisibilityDialogCreateBuildOrTeam(val visibility: Boolean) : MainScreenEvents
    data object OnDialogItemCreateBuildClick : MainScreenEvents
    data object OnDialogItemCreateTeamClick : MainScreenEvents
}