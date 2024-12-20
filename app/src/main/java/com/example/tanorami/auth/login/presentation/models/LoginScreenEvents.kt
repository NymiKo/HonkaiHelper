package com.example.tanorami.auth.login.presentation.models

import com.example.base.UiEvent

sealed interface LoginScreenEvents : UiEvent {
    class LoginChanged(val newValue: String) : LoginScreenEvents
    class PasswordChanged(val newValue: String) : LoginScreenEvents
    data object PasswordVisibilityChange : LoginScreenEvents
    data object Authentication : LoginScreenEvents
    data object OnRegistrationScreen : LoginScreenEvents
    data object OnBack : LoginScreenEvents
}