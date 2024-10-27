package com.example.tanorami.main.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface MainScreenEvents : UiEvent {
    data object DialogButtonOkClick : MainScreenEvents
}