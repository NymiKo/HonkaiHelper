package com.example.tanorami.main.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.core.data.data_store.AppDataStore
import com.example.tanorami.core.network.NetworkResult
import com.example.tanorami.main.data.MainScreenRepository
import com.example.tanorami.main.presentation.models.MainScreenEvents
import com.example.tanorami.main.presentation.models.MainScreenSideEffects
import com.example.tanorami.main.presentation.models.MainScreenUiState
import com.example.tanorami.profile.domain.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val repository: MainScreenRepository,
    private val profileRepository: ProfileRepository,
    private val dataStore: AppDataStore,
) : BaseViewModel<MainScreenUiState, MainScreenEvents, MainScreenSideEffects>(
    initialState = MainScreenUiState()
) {
    init {
        viewModelScope.launch {
            val localVersionDB = dataStore.versionDB.first()
            getRemoteVersionDB(localVersionDB)
        }

        dataStore.tokenUser.onEach {
            if (it.isNotEmpty()) {
                getProfileAvatar()
            } else {
                uiState = uiState.copy(userProfileAvatar = "")
            }
        }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    override fun onEvent(event: MainScreenEvents) {
        when (event) {
            MainScreenEvents.DialogUploadingDataButtonOkClick -> {
                uiState = uiState.copy(dialogUploadingDataVisibilityState = false)
                sendSideEffect(MainScreenSideEffects.OnLoadDataScreen(remoteVersionDB = uiState.remoteVersionDB))
            }

            is MainScreenEvents.ChangeVisibilityDialogCreateBuildOrTeam -> {
                if (uiState.userProfileAvatar.isNotEmpty()) {
                    uiState =
                        uiState.copy(dialogCreateBuildOrTeamVisibilityState = event.visibility)
                } else {
                    sendSideEffect(MainScreenSideEffects.ShowToast(R.string.this_feature_only_registered_users))
                }
            }

            MainScreenEvents.OnDialogItemCreateTeamClick -> {
                sendSideEffect(MainScreenSideEffects.OnCreateTeamScreen)
                uiState = uiState.copy(dialogCreateBuildOrTeamVisibilityState = false)
            }

            MainScreenEvents.OnDialogItemCreateBuildClick -> {
                sendSideEffect(MainScreenSideEffects.CreateBuildForHeroScreen)
                uiState = uiState.copy(dialogCreateBuildOrTeamVisibilityState = false)
            }
        }
    }

    private fun getRemoteVersionDB(localVersionDB: String) = viewModelScope.launch {
        when (val result = repository.getRemoteVersionDB()) {
            is NetworkResult.Error -> {

            }

            is NetworkResult.Success -> {
                if (localVersionDB != result.data.remoteVersionDB) {
                    uiState = uiState.copy(
                        dialogUploadingDataVisibilityState = true,
                        remoteVersionDB = result.data.remoteVersionDB,
                        message = result.data.message
                    )
                }
            }
        }
    }

    private fun getProfileAvatar() = viewModelScope.launch {
        profileRepository.getProfile()
        profileRepository.profileFlow.collect { result ->
            when (result) {
                is NetworkResult.Error -> {
                    uiState = uiState.copy(userProfileAvatar = "")
                }

                is NetworkResult.Success -> {
                    uiState = uiState.copy(userProfileAvatar = result.data.avatarUrl ?: "")
                }

                null -> uiState = uiState.copy(userProfileAvatar = "")
            }
        }
    }
}