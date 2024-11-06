package com.example.tanorami.auth.login.presentation.models

import com.example.tanorami.base.UiState

data class LoginScreenUiState(
    val nicknameField: TextField = TextField(),
    val passwordField: TextField = TextField(visualTransformation = true),
    val isAuthentication: Boolean = false,
) : UiState
