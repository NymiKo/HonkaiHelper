package com.example.tanorami.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.settings.data.SettingsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
): ViewModel() {

    private val _uiState = MutableLiveData<SettingsUIState>(SettingsUIState.DATA_UPDATED)
    val uiState: LiveData<SettingsUIState> = _uiState

    fun checkUpdate(localVersion: String) = viewModelScope.launch {
        _uiState.value = SettingsUIState.LOADING
        when(val result = repository.checkUpdate()) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }
            is NetworkResult.Success -> {
                if (localVersion == result.data) {
                    _uiState.value = SettingsUIState.LATEST_VERSION
                } else {
                    _uiState.value = SettingsUIState.NEW_VERSION(result.data)
                }
            }
        }
    }

    fun dataUpdated() = viewModelScope.launch {
        _uiState.value = SettingsUIState.DATA_UPDATED
    }

    private fun errorHandler(errorCode: Int) {
        when (errorCode) {
            105 -> _uiState.value = SettingsUIState.ERROR(R.string.check_your_internet_connection)
            106 -> _uiState.value = SettingsUIState.ERROR(R.string.error)
            else -> _uiState.value = SettingsUIState.ERROR(R.string.unknown_error)
        }
    }
}