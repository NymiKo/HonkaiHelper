package com.example.honkaihelper.login

import com.example.honkaihelper.teams.TeamsUiState

sealed class LoginUiState<out T> {
    object IDLE: LoginUiState<Nothing>()
    object LOADING: LoginUiState<Nothing>()
    object INCORRECT_PASSWORD: LoginUiState<Nothing>()
    object EMPTY_LOGIN: LoginUiState<Nothing>()
    object EMPTY_PASSWORD: LoginUiState<Nothing>()
    data class SUCCESS<out T>(val token: String): LoginUiState<T>()
    data class ERROR(val message: Int): LoginUiState<Nothing>()
}