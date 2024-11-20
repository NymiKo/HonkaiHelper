package com.example.tanorami.load_data.presentation.models

data class LoadDataScreenUiState(
    val isUploadingData: Boolean = false,
    val isSuccessfulDataUpload: Boolean = false,
    val isErrorDataUpload: Boolean = false,
    val newVersionDB: String = "",
) : com.example.base.UiState
