package com.example.tanorami.auth.login.presentation.models

import com.example.core.base.UiState
import com.example.core.base.models.TextField

data class LoginScreenUiState(
    val nicknameField: TextField = TextField(),
    val passwordField: TextField = TextField(visualTransformation = true),
    val isAuthentication: Boolean = false,
) : UiState
