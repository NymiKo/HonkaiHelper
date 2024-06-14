package com.example.tanorami.profile.presentation

import com.example.tanorami.R
import com.example.tanorami.profile.data.model.User

internal data class ProfileScreenUiState(
    val isLoading: Boolean = true,
    val isAuthorized: Boolean = false,
    val profileData: User = User(),
    val isError: Boolean = false,
    val errorMessage: Int = R.string.error,
)
