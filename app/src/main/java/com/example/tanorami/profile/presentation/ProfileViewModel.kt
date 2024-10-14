package com.example.tanorami.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.AppDataStore
import com.example.tanorami.profile.domain.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val appDataStore: AppDataStore,
) : ViewModel() {

    private var _profileUiState = MutableStateFlow<ProfileScreenUiState>(ProfileScreenUiState.Empty)
    val profileUiState = _profileUiState.asStateFlow()

    init {
        getProfile()
    }

    internal fun onEvent(event: ProfileScreenEvents) {
        when (event) {
            ProfileScreenEvents.LogoutAccount -> logoutAccount()
            ProfileScreenEvents.FetchProfile -> getProfile()
            is ProfileScreenEvents.UploadAvatarOnServer -> loadAvatar(event.file)
            else -> Unit
        }
    }

    private fun getProfile() {
        appDataStore.tokenUser.onEach {
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
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    private fun logoutAccount() = viewModelScope.launch {
        appDataStore.clearToken()
        _profileUiState.value = ProfileScreenUiState.NotAuthorized
    }

    private fun loadAvatar(file: File) = viewModelScope.launch {
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