package com.example.honkaihelper.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.registration.data.RegistrationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val repository: RegistrationRepository
): ViewModel() {

    private val _uiState = MutableLiveData<RegistrationUiState<String>>(RegistrationUiState.IDLE)
    val uiState: LiveData<RegistrationUiState<String>> = _uiState

    fun registration(login: String, password: String) {
        _uiState.value = RegistrationUiState.IDLE
        _uiState.value = RegistrationUiState.LOADING
        if (checkLogin(login) && checkPassword(password)) {
            registrationUser(login, password)
        }
    }

    private fun checkLogin(login: String): Boolean {
        return if (login.isEmpty()) {
            _uiState.value = RegistrationUiState.EMPTY_LOGIN
            false
        } else true
    }

    private fun checkPassword(password: String): Boolean {
        return when {
            password.isEmpty() -> {
                _uiState.value = RegistrationUiState.EMPTY_PASSWORD
                false
            }
            password.length <= 4 -> {
                _uiState.value = RegistrationUiState.INCORRECT_PASSWORD
                false
            }
            else -> true
        }
    }

    private fun registrationUser(login: String, password: String) {
        viewModelScope.launch {
            val result = repository.register(login, password)
            result.onSuccess {
                _uiState.value = RegistrationUiState.SUCCESS(it.token)
            }.onFailure {
                _uiState.value = RegistrationUiState.ERROR
            }
        }
    }
}