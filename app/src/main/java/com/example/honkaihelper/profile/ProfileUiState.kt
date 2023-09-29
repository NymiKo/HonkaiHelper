package com.example.honkaihelper.profile

import com.example.honkaihelper.profile.data.model.User

sealed class ProfileUiState<out T> {
    object LOADING : ProfileUiState<Nothing>()
    data class SUCCESS(val user: User) : ProfileUiState<Nothing>()
    data class ERROR(val message: Int) : ProfileUiState<Nothing>()
    object NOT_AUTHORIZED : ProfileUiState<Nothing>()
}