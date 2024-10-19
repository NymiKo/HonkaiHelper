package com.example.tanorami.load_data.presentation

sealed class LoadDataUiState {
    object LOADING: LoadDataUiState()
    object SUCCESS: LoadDataUiState()
    object ERROR: LoadDataUiState()
}