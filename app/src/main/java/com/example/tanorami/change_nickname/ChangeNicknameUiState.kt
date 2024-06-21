package com.example.tanorami.change_nickname

sealed class ChangeNicknameUiState {
    data object LOADING: ChangeNicknameUiState()
    data class ERROR(val message: Int): ChangeNicknameUiState()
    data object SUCCESS: ChangeNicknameUiState()
    data object IDLE: ChangeNicknameUiState()
}