package com.example.honkaihelper.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class LoginViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState<String>>(LoginUiState.IDLE)
    val uiState: LiveData<LoginUiState<String>> = _uiState

    fun login(login: String, password: String) {
        _uiState.value = LoginUiState.LOADING
        checkLogin(login)
        checkPassword(password)
    }

    private fun checkLogin(login: String) {
        if (login.isEmpty()) {
            _uiState.value = LoginUiState.EMPTY_LOGIN
        }
    }

    private fun checkPassword(password: String) {
        when {
            password.isEmpty() -> {
                _uiState.value = LoginUiState.EMPTY_PASSWORD
            }
            password.length <= 4 -> {
                _uiState.value = LoginUiState.INCORRECT_PASSWORD
            }
        }
    }
}