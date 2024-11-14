package com.example.tanorami.load_data.presentation.models

import com.example.core.base.UiEvent

sealed interface LoadDataScreenEvents : UiEvent {
    class UploadData(val newVersionDB: String) : LoadDataScreenEvents
    data object OnBack : LoadDataScreenEvents
}