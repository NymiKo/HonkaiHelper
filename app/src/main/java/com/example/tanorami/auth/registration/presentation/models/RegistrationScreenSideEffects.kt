package com.example.tanorami.auth.registration.presentation.models

import com.example.base.UiEffect

sealed interface RegistrationScreenSideEffects : UiEffect {
    class ShowToast(val message: Int) : RegistrationScreenSideEffects
    data object SuccessCreatingAccount : RegistrationScreenSideEffects
    data object OnBack : RegistrationScreenSideEffects
}