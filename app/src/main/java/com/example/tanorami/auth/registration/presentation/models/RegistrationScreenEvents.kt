package com.example.tanorami.auth.registration.presentation.models

import com.example.core.base.UiEvent

sealed interface RegistrationScreenEvent : UiEvent {
    class LoginChanged(val newValue: String) : RegistrationScreenEvent
    class PasswordChanged(val newValue: String) : RegistrationScreenEvent
    data object PasswordVisibilityChange : RegistrationScreenEvent
    data object CreateAccount : RegistrationScreenEvent
    data object OnBack : RegistrationScreenEvent
}