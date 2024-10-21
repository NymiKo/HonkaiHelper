package com.example.tanorami.profile.presentation.models

import com.example.tanorami.profile.data.model.User

sealed interface ProfileScreenUiState {
    data object Empty  : ProfileScreenUiState
    data object Loading : ProfileScreenUiState
    data class Success(val user: User) : ProfileScreenUiState
    data class Error(val message: Int) : ProfileScreenUiState
    data object NotAuthorized : ProfileScreenUiState
}