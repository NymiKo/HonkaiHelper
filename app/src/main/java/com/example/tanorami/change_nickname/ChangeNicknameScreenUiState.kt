package com.example.tanorami.change_nickname

import com.example.tanorami.R

data class ChangeNicknameScreenUiState(
    val oldNickname: String = "",
    val newNickname: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: Boolean = false,
    val errorMessage: Int = R.string.error,
)
