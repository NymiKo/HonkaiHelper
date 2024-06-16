package com.example.tanorami.profile.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.UserDataStore
import com.example.tanorami.profile.domain.ProfileRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val userDataStore: UserDataStore,
) : ViewModel() {

    internal var profileUiState by mutableStateOf(ProfileScreenUiState())
        private set

    init {
        viewModelScope.launch  {
            userDataStore.tokenUser.collect {
                if (it == "") {
                    profileUiState = profileUiState.copy(
                        isError = false,
                        isLoading = false,
                        isAuthorized = false,
                    )
                } else {
                    getProfile()
                }
            }
        }
    }

    internal fun onEvent(event: ProfileScreenEvents) {
        when (event) {
            ProfileScreenEvents.LogoutAccount -> logoutAccount()
            ProfileScreenEvents.FetchProfile -> getProfile()
            is ProfileScreenEvents.LoadAvatar -> loadAvatar(event.file)
            else -> Unit
        }
    }

    private fun getProfile() = viewModelScope.launch {
        profileUiState = profileUiState.copy(isLoading = true)
        when (val result = repository.getProfile()) {
            is NetworkResult.Error -> profileUiState = profileUiState.copy(
                isError = true,
                isLoading = false,
                errorMessage = errorHandler(result.code),
            )

            is NetworkResult.Success -> {
                profileUiState = profileUiState.copy(
                    isError = false,
                    isLoading = false,
                    profileData = result.data,
                    isAuthorized = true,
                )
            }
        }
    }

    private fun logoutAccount() = viewModelScope.launch {
        userDataStore.clearToken()
        profileUiState = profileUiState.copy(isAuthorized = false)
    }

    fun loadAvatar(file: File) = viewModelScope.launch {
        repository.loadAvatar(file)
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            106 -> R.string.error
            else -> R.string.unknown_error
        }
    }
}