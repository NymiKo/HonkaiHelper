package com.example.tanorami.auth.registration.presentation.models

import com.example.tanorami.auth.login.presentation.models.TextField
import com.example.tanorami.base.UiState

data class RegistrationScreenUiState(
    val nicknameField: TextField = TextField(),
    val passwordField: TextField = TextField(),
    val isCreatingAccount: Boolean = false,
) : UiState
