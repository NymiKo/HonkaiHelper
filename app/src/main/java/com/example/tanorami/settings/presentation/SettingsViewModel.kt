package com.example.tanorami.settings.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.data.remote.util.NetworkResult
import com.example.domain.data_store.AppDataStore
import com.example.strings.R
import com.example.tanorami.settings.data.SettingsRepository
import com.example.tanorami.settings.presentation.models.SettingsScreenEvents
import com.example.tanorami.settings.presentation.models.SettingsScreenSideEffects
import com.example.tanorami.settings.presentation.models.SettingsScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val dataStore: AppDataStore,
) : BaseViewModel<SettingsScreenUiState, SettingsScreenEvents, SettingsScreenSideEffects>(
    initialState = SettingsScreenUiState()
) {
    init {
        dataStore.versionDB
            .onEach { versionDB ->
                uiState = uiState.copy(versionDB = versionDB)
            }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    override fun onEvent(event: SettingsScreenEvents) {
        when (event) {
            SettingsScreenEvents.CheckUpdate -> checkUpdate()
            SettingsScreenEvents.OnBack -> sendSideEffect(SettingsScreenSideEffects.OnBack)
            SettingsScreenEvents.OnSendFeedbackScreen -> sendSideEffect(SettingsScreenSideEffects.OnSendFeedbackScreen)
            SettingsScreenEvents.DataUpdated -> sendSideEffect(SettingsScreenSideEffects.ShowToast(R.string.data_updated))
            SettingsScreenEvents.CLickDonateButton -> sendSideEffect(SettingsScreenSideEffects.CLickDonateButton)
        }
    }

    private fun checkUpdate() = viewModelScope.launch {
        uiState = uiState.copy(isLoading = true)
        when (val result = repository.checkUpdate()) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(isLoading = false, isError = true)
                sendSideEffect(SettingsScreenSideEffects.ShowToast(errorHandler(result.code)))
            }

            is NetworkResult.Success -> {
                uiState = uiState.copy(isLoading = false, isError = false)
                if (uiState.versionDB == result.data) {
                    sendSideEffect(SettingsScreenSideEffects.ShowToast(R.string.already_have_latest_version_db))
                } else {
                    sendSideEffect(SettingsScreenSideEffects.OnLoadDataScreen(result.data))
                }
            }
        }
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            106 -> R.string.error
            else -> R.string.unknown_error
        }
    }
}