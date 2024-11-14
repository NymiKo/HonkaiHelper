package com.example.tanorami.change_nickname.presentation.models

import com.example.core.R
import com.example.core.base.models.TextField

data class ChangeNicknameScreenUiState(
    val oldNickname: String = "",
    val newNickname: TextField = TextField(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val message: Int = R.string.error,
)
