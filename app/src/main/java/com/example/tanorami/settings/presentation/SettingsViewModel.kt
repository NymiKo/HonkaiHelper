package com.example.tanorami.settings.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.domain.data_store.AppDataStore
import com.example.domain.util.NetworkResult
import com.example.strings.R
import com.example.tanorami.settings.data.SettingsRepository
import com.example.tanorami.settings.presentation.models.SettingsScreenEvents
import com.example.tanorami.settings.presentation.models.SettingsScreenSideEffects
import com.example.tanorami.settings.presentation.models.SettingsScreenSideEffects.*
import com.example.tanorami.settings.presentation.models.SettingsScreenUiState
import kotlinx.coroutines.flow.first
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
            .launchIn(viewModelScope)
//        dataStore.showSnowfallAnimation
//            .onEach {
//                uiState = uiState.copy(showSnowfallAnimation = it)
//            }
//            .launchIn(viewModelScope)
        viewModelScope.launch {
            uiState = uiState.copy(
                countSnowflakes = dataStore.countSnowflakes.first(),
                showSnowfallAnimation = dataStore.showSnowfallAnimation.first()
            )
        }
    }

    override fun onEvent(event: SettingsScreenEvents) {
        when (event) {
            SettingsScreenEvents.CheckUpdate -> checkUpdate()
            SettingsScreenEvents.OnBack -> sendSideEffect(OnBack)
            SettingsScreenEvents.OnSendFeedbackScreen -> sendSideEffect(OnSendFeedbackScreen)
            SettingsScreenEvents.DataUpdated -> sendSideEffect(ShowToast(R.string.data_updated))
            SettingsScreenEvents.CLickDonateButton -> sendSideEffect(CLickDonateButton)
            SettingsScreenEvents.ChangeStateSnowfallAnimation -> {
                viewModelScope.launch {
                    dataStore.saveSettingsSnowfallAnimation(!uiState.showSnowfallAnimation)
                    uiState = uiState.copy(showSnowfallAnimation = !uiState.showSnowfallAnimation)
                }
            }

            is SettingsScreenEvents.ChangeCountSnowflakesAnimation -> {
                viewModelScope.launch {
                    uiState = uiState.copy(countSnowflakes = event.count)
                    dataStore.saveCountSnowflakesAnimation(event.count)
                }
            }
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