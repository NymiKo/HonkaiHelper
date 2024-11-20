package com.example.tanorami.load_data.presentation.models

sealed interface LoadDataScreenEvents : com.example.base.UiEvent {
    class UploadData(val newVersionDB: String) : LoadDataScreenEvents
    data object OnBack : LoadDataScreenEvents
}