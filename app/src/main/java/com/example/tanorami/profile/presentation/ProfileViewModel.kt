package com.example.tanorami.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.UserDataStore
import com.example.tanorami.profile.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val userDataStore: UserDataStore,
) : ViewModel() {

    private var _profileUiState = MutableStateFlow<ProfileScreenUiState>(ProfileScreenUiState.Empty)
    val profileUiState = _profileUiState.asStateFlow()

    init {
        viewModelScope.launch {
            userDataStore.tokenUser.collect {
                if (it == "") {
                    _profileUiState.value = ProfileScreenUiState.NotAuthorized
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
            is ProfileScreenEvents.UploadAvatarOnServer -> loadAvatar(event.file)
            else -> Unit
        }
    }

    private fun getProfile() = viewModelScope.launch {
        userDataStore.tokenUser.collect {
            if (it == "") {
                _profileUiState.value = ProfileScreenUiState.NotAuthorized
            } else {
                _profileUiState.value = ProfileScreenUiState.Loading
                when (val result = repository.getProfile()) {
                    is NetworkResult.Error -> _profileUiState.value = ProfileScreenUiState.Error(result.code)

                    is NetworkResult.Success -> _profileUiState.value = ProfileScreenUiState.Success(result.data)
                }
            }
        }
    }

    private fun logoutAccount() = viewModelScope.launch {
        userDataStore.clearToken()
        _profileUiState.value = ProfileScreenUiState.NotAuthorized
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