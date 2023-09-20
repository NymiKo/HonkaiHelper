package com.example.honkaihelper.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.login.data.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState<String>>(LoginUiState.IDLE)
    val uiState: LiveData<LoginUiState<String>> = _uiState

    fun login(login: String, password: String) {
        _uiState.value = LoginUiState.IDLE
        _uiState.value = LoginUiState.LOADING
        if (checkLogin(login) && checkPassword(password)) {
            loginUser(login, password)
        }
    }

    private fun checkLogin(login: String): Boolean {
        return if (login.isEmpty()) {
            _uiState.value = LoginUiState.EMPTY_LOGIN
            false
        } else true
    }

    private fun checkPassword(password: String): Boolean {
        return when {
            password.isEmpty() -> {
                _uiState.value = LoginUiState.EMPTY_PASSWORD
                false
            }
            password.length <= 4 -> {
                _uiState.value = LoginUiState.INCORRECT_PASSWORD
                false
            }
            else -> true
        }
    }

    private fun loginUser(login: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(login, password)
            result.onSuccess {
                _uiState.value = LoginUiState.SUCCESS(it.token)
            }.onFailure {
                _uiState.value = LoginUiState.ERROR
            }
        }
    }
}