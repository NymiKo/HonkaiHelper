package com.example.tanorami.change_nickname

sealed class ChangeNicknameUiState {
    object LOADING: ChangeNicknameUiState()
    data class ERROR(val message: Int): ChangeNicknameUiState()
    object SUCCESS: ChangeNicknameUiState()
}