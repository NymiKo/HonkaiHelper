package com.example.honkaihelper.load_data

sealed class LoadDataUiState {
    object LOADING: LoadDataUiState()
    object SUCCESS: LoadDataUiState()
    object ERROR: LoadDataUiState()
}