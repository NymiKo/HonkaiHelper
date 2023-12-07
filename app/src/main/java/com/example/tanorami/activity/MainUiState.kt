package com.example.tanorami.activity

sealed class MainUiState {
    data class DATA_NOT_CORRECTLY(val version: String) : MainUiState()
    object ERROR : MainUiState()
}