package com.example.tanorami.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.login.data.LoginRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState<Any>>(LoginUiState.IDLE)
    val uiState: LiveData<LoginUiState<Any>> = _uiState

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
            password.length < 4 -> {
                _uiState.value = LoginUiState.INCORRECT_PASSWORD
                false
            }
            else -> true
        }
    }

    private fun loginUser(login: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(login, password)
            when(result) {
                is NetworkResult.Error -> errorHandler(result.code)
                is NetworkResult.Success -> _uiState.value = LoginUiState.SUCCESS(result.data.token)
            }
        }
    }

    private fun errorHandler(errorCode: Int) {
        when(errorCode) {
            105 -> _uiState.value = LoginUiState.ERROR(R.string.check_your_internet_connection)
            400 -> _uiState.value = LoginUiState.ERROR(R.string.unknown_user)
            else -> _uiState.value = LoginUiState.ERROR(R.string.unknown_error)
        }
    }
}