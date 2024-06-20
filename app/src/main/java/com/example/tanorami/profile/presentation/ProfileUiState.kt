package com.example.tanorami.profile.presentation

import com.example.tanorami.profile.data.model.User

sealed class ProfileUiState<out T> {
    data object Loading : ProfileUiState<Nothing>()
    data class Success(val user: User) : ProfileUiState<Nothing>()
    data class Error(val message: Int) : ProfileUiState<Nothing>()
    data object NotAuthorized : ProfileUiState<Nothing>()
}