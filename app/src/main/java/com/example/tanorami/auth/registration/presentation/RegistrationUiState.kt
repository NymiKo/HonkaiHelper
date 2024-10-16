package com.example.tanorami.auth.registration.presentation

sealed class RegistrationUiState<out T> {
    object IDLE : RegistrationUiState<Nothing>()
    object LOADING : RegistrationUiState<Nothing>()
    object EMPTY_LOGIN : RegistrationUiState<Nothing>()
    object EMPTY_PASSWORD : RegistrationUiState<Nothing>()
    object INCORRECT_LOGIN : RegistrationUiState<Nothing>()
    object INCORRECT_PASSWORD : RegistrationUiState<Nothing>()
    object SUCCESS : RegistrationUiState<Nothing>()
    data class ERROR(val message: Int) : RegistrationUiState<Int>()
}