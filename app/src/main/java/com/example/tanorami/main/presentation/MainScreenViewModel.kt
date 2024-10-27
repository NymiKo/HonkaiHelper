package com.example.tanorami.main.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.data_store.AppDataStore
import com.example.tanorami.main.data.MainScreenRepository
import com.example.tanorami.main.presentation.models.MainScreenEvents
import com.example.tanorami.main.presentation.models.MainScreenSideEffects
import com.example.tanorami.main.presentation.models.MainScreenUiState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val repository: MainScreenRepository,
    private val dataStore: AppDataStore,
) : BaseViewModel<MainScreenUiState, MainScreenEvents, MainScreenSideEffects>(
    initialState = MainScreenUiState()
) {
    init {
        viewModelScope.launch {
            val localVersionDB = dataStore.versionDB.first()
            getRemoteVersionDB(localVersionDB)
        }

        viewModelScope.launch {
            val token = dataStore.tokenUser.first()
            if (token.isNotEmpty()) {
                when (val result = repository.getAvatar()) {
                    is NetworkResult.Error -> {
                        uiState = uiState.copy(userProfileAvatar = "")
                    }

                    is NetworkResult.Success -> {
                        uiState = uiState.copy(userProfileAvatar = result.data)
                    }
                }
            } else {
                uiState = uiState.copy(userProfileAvatar = "")
            }
        }
    }

    private fun getRemoteVersionDB(localVersionDB: String) = viewModelScope.launch {
        when (val result = repository.getRemoteVersionDB()) {
            is NetworkResult.Error -> {

            }

            is NetworkResult.Success -> {
                if (localVersionDB != result.data) {
                    sendSideEffect(MainScreenSideEffects.OnLoadDataScreen(remoteVersionDB = result.data))
                }
            }
        }
    }

    override fun onEvent(event: MainScreenEvents) {

    }
}