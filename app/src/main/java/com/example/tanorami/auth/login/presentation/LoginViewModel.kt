package com.example.tanorami.auth.login.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.auth.login.domain.LoginRepository
import com.example.tanorami.auth.login.presentation.models.LoginScreenEvents
import com.example.tanorami.auth.login.presentation.models.LoginScreenSideEffects
import com.example.tanorami.auth.login.presentation.models.LoginScreenUiState
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.data_store.AppDataStore
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
                loginField = uiState.loginField.copy(
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

            LoginScreenEvents.Authentication -> authentication()
            LoginScreenEvents.OnRegistrationScreen -> sendSideEffect(LoginScreenSideEffects.OnRegistrationScreen)
        }
    }

    private fun authentication() {
        if (checkLogin(uiState.loginField.value) && checkPassword(uiState.passwordField.value)) {
            loginUser(uiState.loginField.value, uiState.passwordField.value)
        }
    }

    private fun checkLogin(login: String): Boolean {
        return if (login.isEmpty()) {
            uiState = uiState.copy(
                loginField = uiState.loginField.copy(
                    isError = true,
                    errorMessage = R.string.empty_login
                )
            )
            false
        } else true
    }

    private fun checkPassword(password: String): Boolean {
        return when {
            password.isEmpty() -> {
                uiState = uiState.copy(
                    passwordField = uiState.passwordField.copy(
                        isError = true,
                        errorMessage = R.string.empty_password
                    )
                )
                false
            }

            password.length < 4 -> {
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

    private fun loginUser(login: String, password: String) = viewModelScope.launch {
        uiState = uiState.copy(isAuthentication = true)
        when (val result = repository.login(login, password)) {
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