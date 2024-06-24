package com.example.tanorami.change_nickname.presentation

import com.example.tanorami.R

data class ChangeNicknameScreenUiState(
    val oldNickname: String = "",
    val newNickname: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: Int = R.string.error,
)
