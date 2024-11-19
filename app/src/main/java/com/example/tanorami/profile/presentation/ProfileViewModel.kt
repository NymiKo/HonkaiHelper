package com.example.tanorami.profile.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.domain.data_store.AppDataStore
import com.example.strings.R
import com.example.tanorami.profile.domain.ProfileRepository
import com.example.tanorami.profile.presentation.models.ProfileScreenEvents
import com.example.tanorami.profile.presentation.models.ProfileScreenSideEffects
import com.example.tanorami.profile.presentation.models.ProfileScreenUiState
import kotlinx.coroutines.flow.first
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

    private fun getProfileFlow() = viewModelScope.launch {
        repository.profileFlow.collect { result ->
            when (result) {
                is com.example.data.remote.NetworkResult.Error -> uiState = uiState.copy(
                    isLoading = false,
                    isSuccess = false,
                    isError = true,
                    message = errorHandler(result.code),
                )

                is com.example.data.remote.NetworkResult.Success -> uiState = uiState.copy(
                    isLoading = false,
                    isSuccess = true,
                    isError = false,
                    user = result.data,
                )

                null -> uiState = uiState.copy(
                    isLoading = false,
                    isSuccess = false,
                    isError = false,
                    isAuthorized = false,
                )
            }
        }
    }

    private fun getProfile() = viewModelScope.launch {
        val token = appDataStore.tokenUser.first()
        if (token.isNotEmpty()) {
            uiState = uiState.copy(isLoading = true, isAuthorized = true)
            repository.getProfile()
        } else {
            uiState = uiState.copy(isAuthorized = false)
        }
    }

    private fun logoutAccount() = viewModelScope.launch {
        appDataStore.clearToken()
        repository.clearProfile()
        uiState = uiState.copy(
            isAuthorized = false,
            isSuccess = false,
            isError = false,
            isLoading = false
        )
    }

    private fun loadAvatar(file: File) = viewModelScope.launch {
        when (repository.loadAvatar(file)) {
            is com.example.data.remote.NetworkResult.Error -> {
                sendSideEffect(ProfileScreenSideEffects.ShowToastError(R.string.error_upload_avatar))
            }

            is com.example.data.remote.NetworkResult.Success -> {
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