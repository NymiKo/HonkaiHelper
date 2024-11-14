package com.example.tanorami.auth.login.presentation.models

import com.example.core.base.UiEffect

sealed interface LoginScreenSideEffects : UiEffect {
    data object OnRegistrationScreen : LoginScreenSideEffects
    data object OnBack : LoginScreenSideEffects
    class ShowToast(val message: Int) : LoginScreenSideEffects
}