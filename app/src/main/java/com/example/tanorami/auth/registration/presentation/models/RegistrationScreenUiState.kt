package com.example.tanorami.auth.registration.presentation.models

import com.example.base.UiState
import com.example.base.models.TextField

data class RegistrationScreenUiState(
    val nicknameField: TextField = TextField(),
    val passwordField: TextField = TextField(visualTransformation = true),
    val isCreatingAccount: Boolean = false,
) : UiState
