package com.example.tanorami.auth.login.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.auth.login.domain.LoginRepository
import com.example.tanorami.auth.login.presentation.models.LoginScreenEvents
import com.example.tanorami.auth.login.presentation.models.LoginScreenSideEffects
import com.example.tanorami.auth.login.presentation.models.LoginScreenUiState
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.core.data.data_store.AppDataStore
import com.example.tanorami.core.network.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val appDataStore: AppDataStore
) : BaseViewModel<LoginScreenUiState, LoginScreenEvents, LoginScreenSideEffects>(
    initialState = LoginScreenUiState()
) {
    override fun onEvent(event: LoginScreenEvents) {
        when (event) {
            LoginScreenEvents.OnBack -> sendSideEffect(LoginScreenSideEffects.OnBack)
            is LoginScreenEvents.LoginChanged -> uiState = uiState.copy(
                nicknameField = uiState.nicknameField.copy(
                    value = event.newValue,
                    isError = false
                )
            )

            is LoginScreenEvents.PasswordChanged -> uiState =
                uiState.copy(
                    passwordField = uiState.passwordField.copy(
                        value = event.newValue,
                        isError = false
                    )
                )

            LoginScreenEvents.PasswordVisibilityChange -> uiState =
                uiState.copy(passwordField = uiState.passwordField.copy(visualTransformation = !uiState.passwordField.visualTransformation))

            LoginScreenEvents.Authentication -> authentication()
            LoginScreenEvents.OnRegistrationScreen -> sendSideEffect(LoginScreenSideEffects.OnRegistrationScreen)
        }
    }

    private fun authentication() {
        if (checkLogin() && checkPassword()) {
            loginUser()
        }
    }

    private fun checkLogin(): Boolean {
        return if (uiState.nicknameField.value.isEmpty()) {
            uiState = uiState.copy(
                nicknameField = uiState.nicknameField.copy(
                    isError = true,
                    errorMessage = R.string.empty_login
                )
            )
            false
        } else true
    }

    private fun checkPassword(): Boolean {
        return if (uiState.passwordField.value.isEmpty()) {
            uiState = uiState.copy(
                passwordField = uiState.passwordField.copy(
                    isError = true,
                    errorMessage = R.string.empty_password
                )
            )
            false
        } else true
    }

    private fun loginUser() = viewModelScope.launch {
        uiState = uiState.copy(isAuthentication = true)
        when (val result =
            repository.login(uiState.nicknameField.value, uiState.passwordField.value)) {
            is NetworkResult.Error -> {
                sendSideEffect(LoginScreenSideEffects.ShowToast(errorHandler(result.code)))
                uiState = uiState.copy(isAuthentication = false)
            }

            is NetworkResult.Success -> {
                appDataStore.saveToken(result.data.token)
                sendSideEffect(LoginScreenSideEffects.OnBack)
            }
        }
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            400 -> R.string.unknown_user
            else -> R.string.unknown_error
        }
    }
}