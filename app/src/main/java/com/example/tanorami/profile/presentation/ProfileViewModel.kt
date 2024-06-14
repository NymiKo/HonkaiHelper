package com.example.tanorami.profile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.profile.domain.ProfileRepository
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<ProfileUiState<Any>>(ProfileUiState.NOT_AUTHORIZED)
    val uiState: LiveData<ProfileUiState<Any>> = _uiState

    internal var profileUiState by mutableStateOf(ProfileScreenUiState())
        private set

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
                isAuthorized = true,
            )

            is NetworkResult.Success -> profileUiState = profileUiState.copy(
                isError = false,
                isLoading = false,
                profileData = result.data,
                isAuthorized = true,
            )
        }
    }

    private fun logoutAccount() = viewModelScope.launch {
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