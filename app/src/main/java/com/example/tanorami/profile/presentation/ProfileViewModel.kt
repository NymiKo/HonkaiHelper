package com.example.tanorami.profile.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.core.data.NetworkResult
import com.example.tanorami.core.data.data_store.AppDataStore
import com.example.tanorami.profile.domain.ProfileRepository
import com.example.tanorami.profile.presentation.models.ProfileScreenEvents
import com.example.tanorami.profile.presentation.models.ProfileScreenSideEffects
import com.example.tanorami.profile.presentation.models.ProfileScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val appDataStore: AppDataStore,
) : BaseViewModel<ProfileScreenUiState, ProfileScreenEvents, ProfileScreenSideEffects>(
    initialState = ProfileScreenUiState()
) {
    init {
        getProfile()
        getProfileFlow()
    }

    override fun onEvent(event: ProfileScreenEvents) {
        when (event) {
            ProfileScreenEvents.LogoutAccount -> logoutAccount()
            ProfileScreenEvents.FetchProfile -> getProfile()
            is ProfileScreenEvents.UploadAvatarOnServer -> loadAvatar(event.file)
            ProfileScreenEvents.OnChangeNicknameScreen -> sendSideEffect(
                ProfileScreenSideEffects.OnChangeNicknameScreen(
                    uiState.user.nickname
                )
            )

            is ProfileScreenEvents.OnEditBuildHeroScreen -> sendSideEffect(
                ProfileScreenSideEffects.OnEditBuildHeroScreen(
                    event.idBuild
                )
            )

            is ProfileScreenEvents.OnEditTeamScreen -> sendSideEffect(
                ProfileScreenSideEffects.OnEditTeamScreen(
                    event.idTeam
                )
            )

            ProfileScreenEvents.OnLoginScreen -> sendSideEffect(ProfileScreenSideEffects.OnLoginScreen)
        }
    }

    private fun getProfileFlow() {
        appDataStore.tokenUser.onEach {
            if (it.isEmpty()) {
                uiState = uiState.copy(isAuthorized = false)
            } else {
                uiState = uiState.copy(isLoading = true)
                repository.profileFlow.collect { result ->
                    when (result) {
                        is NetworkResult.Error -> uiState = uiState.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            isAuthorized = true,
                            message = errorHandler(result.code),
                        )

                        is NetworkResult.Success -> uiState = uiState.copy(
                            isLoading = false,
                            isSuccess = true,
                            isAuthorized = true,
                            isError = false,
                            user = result.data
                        )

                        null -> {}
                    }
                }
            }
        }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    private fun getProfile() = viewModelScope.launch {
        repository.getProfile()
    }

    private fun logoutAccount() = viewModelScope.launch {
        appDataStore.clearToken()
        uiState = uiState.copy(
            isAuthorized = false,
            isSuccess = false,
            isError = false,
            isLoading = false
        )
    }

    private fun loadAvatar(file: File) = viewModelScope.launch {
        when (repository.loadAvatar(file)) {
            is NetworkResult.Error -> {
                sendSideEffect(ProfileScreenSideEffects.ShowToastError(R.string.error_upload_avatar))
            }

            is NetworkResult.Success -> {
                getProfile()
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