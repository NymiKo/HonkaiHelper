package com.example.tanorami.profile.presentation.models

import com.example.core.R
import com.example.core.base.UiState
import com.example.tanorami.profile.data.model.User

data class ProfileScreenUiState(
    val isLoading: Boolean = false,
    val isAuthorized: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val message: Int = R.string.error,
    val user: User = User(),
) : UiState
