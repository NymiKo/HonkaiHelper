package com.example.tanorami.auth.registration.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface RegistrationScreenEvent : UiEvent {
    class LoginChanged(val newValue: String) : RegistrationScreenEvent
    class PasswordChanged(val newValue: String) : RegistrationScreenEvent
    data object CreateAccount : RegistrationScreenEvent
    data object OnBack : RegistrationScreenEvent
}