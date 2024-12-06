package com.example.tanorami.auth.login.presentation.models

import com.example.base.UiState
import com.example.base.models.TextField
import com.example.strings.R

data class LoginScreenUiState(
    val nicknameField: TextField = TextField(errorMessage = R.string.empty_login),
    val passwordField: TextField = TextField(
        errorMessage = R.string.empty_password,
        visualTransformation = true
    ),
    val isAuthentication: Boolean = false,
) : UiState
