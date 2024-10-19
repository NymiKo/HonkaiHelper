package com.example.tanorami.load_data.presentation.models

import com.example.tanorami.base.UiState

data class LoadDataScreenUiState(
    val isUploadingData: Boolean = false,
    val isSuccessfulDataUpload: Boolean = false,
    val isErrorDataUpload: Boolean = false,
    val newVersionDB: String = "",
) : UiState
