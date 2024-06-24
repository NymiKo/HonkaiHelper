package com.example.tanorami.change_nickname.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.change_nickname.data.ChangeNicknameRepository
import com.example.tanorami.data.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeNicknameViewModel @Inject constructor(
    private val repository: ChangeNicknameRepository
) : ViewModel() {
    var uiState by mutableStateOf(ChangeNicknameScreenUiState())

    fun onEvent(event: ChangeNicknameScreenEvents) {
        when (event) {
            ChangeNicknameScreenEvents.ChangeNickname -> changeNickname()
            is ChangeNicknameScreenEvents.EnteringNickname -> uiState =
                uiState.copy(newNickname = event.nickname)

            else -> Unit
        }
    }

    private fun changeNickname() = viewModelScope.launch {
        if (checkNickname(uiState.newNickname) && comparisonNickname(
                uiState.oldNickname,
                uiState.newNickname
            )
        ) {
            uiState = uiState.copy(loading = true, success = false, error = false)
            when (val result = repository.changeNickname(uiState.newNickname)) {
                is NetworkResult.Error -> {
                    uiState = uiState.copy(
                        loading = false,
                        success = false,
                        error = true,
                        errorMessage = errorHandler(result.code)
                    )
                }

                is NetworkResult.Success -> {
                    uiState = uiState.copy(loading = false, success = true, error = false)
                }
            }
        }
    }

    private fun checkNickname(newNickname: String): Boolean {
        return if (newNickname.isEmpty()) {
            uiState = uiState.copy(
                loading = false,
                success = false,
                error = true,
                errorMessage = R.string.empty_new_nickname
            )
            false
        } else true
    }

    private fun comparisonNickname(oldNickname: String, newNickname: String): Boolean {
        return if (newNickname == oldNickname) {
            uiState = uiState.copy(
                loading = false,
                success = false,
                error = true,
                errorMessage = R.string.already_have_nickname
            )
            false
        } else true
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            106 -> R.string.error
            400 -> R.string.nickname_already_in_use
            504 -> R.string.error_save_in_server
            else -> R.string.unknown_error
        }
    }
}