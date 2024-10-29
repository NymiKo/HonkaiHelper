package com.example.tanorami.profile.presentation.models

import com.example.tanorami.profile.data.model.User

sealed interface ProfileScreUiState {
    data object Empty : ProfileScreUiState
    data object Loading : ProfileScreUiState
    data class Success(val user: User) : ProfileScreUiState
    data class Error(val message: Int) : ProfileScreUiState
    data object NotAuthorized : ProfileScreUiState
}