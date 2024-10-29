package com.example.tanorami.change_nickname.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.change_nickname.data.ChangeNicknameRepository
import com.example.tanorami.change_nickname.presentation.models.ChangeNicknameScreenEvents
import com.example.tanorami.change_nickname.presentation.models.ChangeNicknameScreenUiState
import com.example.tanorami.core.data.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeNicknameViewModel @Inject constructor(
    private val repository: ChangeNicknameRepository
) : ViewModel() {
    var uiState by mutableStateOf(ChangeNicknameScreenUiState())

    fun onEvent(event: ChangeNicknameScreenEvents) {
        when (event) {
            ChangeNicknameScreenEvents.ChangeNickname -> changeNickname()
            is ChangeNicknameScreenEvents.NicknameChanged -> uiState =
                uiState.copy(
                    newNickname = uiState.newNickname.copy(
                        value = event.newValue,
                        isError = false
                    )
                )

            is ChangeNicknameScreenEvents.SetOldNickname -> {
                uiState = uiState.copy(
                    oldNickname = event.oldNickname,
                    newNickname = uiState.newNickname.copy(
                        value = event.oldNickname,
                        isError = false
                    )
                )
            }

            else -> Unit
        }
    }

    private fun changeNickname() = viewModelScope.launch {
        if (checkNickname(uiState.newNickname.value) && comparisonNickname(
                uiState.oldNickname,
                uiState.newNickname.value
            )
        ) {
            uiState = uiState.copy(isLoading = true, isSuccess = false, isError = false)
            when (val result = repository.changeNickname(uiState.newNickname.value)) {
                is NetworkResult.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = true,
                        message = errorHandler(result.code)
                    )
                }

                is NetworkResult.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false,
                        message = R.string.nickname_changed
                    )
                }
            }
        }
    }

    private fun checkNickname(newNickname: String): Boolean {
        return if (newNickname.isEmpty()) {
            uiState = uiState.copy(
                isLoading = false,
                isSuccess = false,
                newNickname = uiState.newNickname.copy(
                    isError = true,
                    errorMessage = R.string.empty_new_nickname
                ),
            )
            false
        } else true
    }

    private fun comparisonNickname(oldNickname: String, newNickname: String): Boolean {
        return if (newNickname == oldNickname) {
            uiState = uiState.copy(
                isLoading = false,
                isSuccess = false,
                newNickname = uiState.newNickname.copy(
                    isError = true,
                    errorMessage = R.string.already_have_nickname
                ),
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