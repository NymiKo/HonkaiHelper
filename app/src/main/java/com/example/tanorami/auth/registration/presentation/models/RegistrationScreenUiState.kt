package com.example.tanorami.auth.registration.presentation.models

import com.example.core.base.models.TextField
import com.example.core.base.UiState

data class RegistrationScreenUiState(
    val nicknameField: TextField = TextField(),
    val passwordField: TextField = TextField(visualTransformation = true),
    val isCreatingAccount: Boolean = false,
) : UiState
