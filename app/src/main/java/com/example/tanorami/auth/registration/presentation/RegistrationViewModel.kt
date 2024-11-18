package com.example.tanorami.auth.registration.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.base.BaseViewModel
import com.example.tanorami.auth.registration.data.RegistrationRepository
import com.example.tanorami.auth.registration.presentation.models.RegistrationScreenEvent
import com.example.tanorami.auth.registration.presentation.models.RegistrationScreenSideEffects
import com.example.tanorami.auth.registration.presentation.models.RegistrationScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val repository: RegistrationRepository
) : BaseViewModel<RegistrationScreenUiState, RegistrationScreenEvent, RegistrationScreenSideEffects>(
    initialState = RegistrationScreenUiState()
) {
    override fun onEvent(event: RegistrationScreenEvent) {
        when (event) {
            RegistrationScreenEvent.CreateAccount -> createAccount()

            is RegistrationScreenEvent.LoginChanged -> uiState = uiState.copy(
                nicknameField = uiState.nicknameField.copy(
                    value = event.newValue,
                    isError = false
                )
            )

            RegistrationScreenEvent.OnBack -> sendSideEffect(RegistrationScreenSideEffects.OnBack)

            is RegistrationScreenEvent.PasswordChanged -> uiState = uiState.copy(
                passwordField = uiState.passwordField.copy(
                    value = event.newValue,
                    isError = false
                )
            )

            RegistrationScreenEvent.PasswordVisibilityChange -> uiState =
                uiState.copy(passwordField = uiState.passwordField.copy(visualTransformation = !uiState.passwordField.visualTransformation))
        }
    }

    private fun createAccount() {
        if (checkLogin() && checkPassword()) {
            registrationUser()
        }
    }

    private fun checkLogin(): Boolean {
        return when {
            uiState.nicknameField.value.isEmpty() -> {
                uiState = uiState.copy(
                    nicknameField = uiState.nicknameField.copy(
                        isError = true,
                        errorMessage = R.string.empty_login
                    )
                )
                false
            }

            uiState.nicknameField.value.length > 50 -> {
                uiState = uiState.copy(
                    nicknameField = uiState.nicknameField.copy(
                        isError = true,
                        errorMessage = R.string.incorrect_login
                    )
                )
                false
            }

            else -> true
        }
    }

    private fun checkPassword(): Boolean {
        return when {
            uiState.passwordField.value.isEmpty() -> {
                uiState = uiState.copy(
                    passwordField = uiState.passwordField.copy(
                        isError = true,
                        errorMessage = R.string.empty_password
                    )
                )
                false
            }

            uiState.passwordField.value.length < 4 -> {
                uiState = uiState.copy(
                    passwordField = uiState.passwordField.copy(
                        isError = true,
                        errorMessage = R.string.incorrect_password
                    )
                )
                false
            }

            else -> true
        }
    }

    private fun registrationUser() = viewModelScope.launch {
        uiState = uiState.copy(isCreatingAccount = true)
        when (val result =
            repository.register(uiState.nicknameField.value, uiState.passwordField.value)) {

            is com.example.data.remote.NetworkResult.Success -> {
                sendSideEffect(RegistrationScreenSideEffects.SuccessCreatingAccount)
                uiState = uiState.copy(isCreatingAccount = false)
            }

            is com.example.data.remote.NetworkResult.Error -> {
                sendSideEffect(RegistrationScreenSideEffects.ShowToast(errorHandler(result.code)))
                uiState = uiState.copy(isCreatingAccount = false)
            }
        }
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            400 -> R.string.login_already_exists
            else -> R.string.unknown_error
        }
    }
}