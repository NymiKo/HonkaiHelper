package com.example.tanorami.load_data.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.domain.data_store.AppDataStore
import com.example.tanorami.load_data.data.LoadDataRepository
import com.example.tanorami.load_data.presentation.models.LoadDataScreenEvents
import com.example.tanorami.load_data.presentation.models.LoadDataScreenSideEffects
import com.example.tanorami.load_data.presentation.models.LoadDataScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadDataViewModel @Inject constructor(
    private val repository: LoadDataRepository,
    private val dataStore: AppDataStore,
) : BaseViewModel<LoadDataScreenUiState, LoadDataScreenEvents, LoadDataScreenSideEffects>(
    initialState = LoadDataScreenUiState()
) {
    override fun onEvent(event: LoadDataScreenEvents) {
        when (event) {
            is LoadDataScreenEvents.UploadData -> uploadData(event.newVersionDB)
            LoadDataScreenEvents.OnBack -> sendSideEffect(LoadDataScreenSideEffects.OnBack)
        }
    }

    private fun uploadData(newVersionDB: String) = viewModelScope.launch {
        uiState = uiState.copy(isUploadingData = true, newVersionDB = newVersionDB)
        if (repository.downloadingData()) {
            dataStore.saveVersionDB(newVersionDB)
            sendSideEffect(LoadDataScreenSideEffects.OnBack)
        } else {
            uiState = uiState.copy(
                isUploadingData = false,
                isSuccessfulDataUpload = false,
                isErrorDataUpload = true
            )
        }
    }
}