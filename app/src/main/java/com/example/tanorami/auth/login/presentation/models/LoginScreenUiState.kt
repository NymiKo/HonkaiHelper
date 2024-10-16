package com.example.tanorami.auth.login.presentation.models

import com.example.tanorami.base.UiState

data class LoginScreenUiState(
    val loginField: TextField = TextField(),
    val passwordField: TextField = TextField(),
    val isAuthentication: Boolean = false,
) : UiState
