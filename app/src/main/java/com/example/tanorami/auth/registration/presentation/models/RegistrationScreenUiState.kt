package com.example.tanorami.auth.registration.presentation.models

import com.example.base.UiState
import com.example.base.models.TextField
import com.example.strings.R

data class RegistrationScreenUiState(
    val nicknameField: TextField = TextField(errorMessage = R.string.empty_login),
    val passwordField: TextField = TextField(
        errorMessage = R.string.empty_password,
        visualTransformation = true
    ),
    val isCreatingAccount: Boolean = false,
) : UiState
