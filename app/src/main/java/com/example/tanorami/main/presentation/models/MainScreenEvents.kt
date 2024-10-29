package com.example.tanorami.main.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface MainScreenEvents : UiEvent {
    data object DialogUploadingDataButtonOkClick : MainScreenEvents
    class ChangeVisibilityDialogCreateBuildOrTeam(val visibility: Boolean) : MainScreenEvents
    data object OnDialogItemCreateBuildClick : MainScreenEvents
    data object OnDialogItemCreateTeamClick : MainScreenEvents
}