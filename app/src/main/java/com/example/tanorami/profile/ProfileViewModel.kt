package com.example.tanorami.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.profile.data.ProfileRepository
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {

    private val _uiState = MutableLiveData<ProfileUiState<Any>>(ProfileUiState.NOT_AUTHORIZED)
    val uiState: LiveData<ProfileUiState<Any>> = _uiState

    fun getProfile() = viewModelScope.launch {
        _uiState.value = ProfileUiState.LOADING
        val result = repository.getProfile()
        when(result) {
            is NetworkResult.Error -> errorHandler(result.code)
            is NetworkResult.Success -> _uiState.value = ProfileUiState.SUCCESS(result.data)
        }
    }

    fun logoutAccount() = viewModelScope.launch {
        _uiState.value = ProfileUiState.NOT_AUTHORIZED
    }

    fun loadAvatar(file: File) = viewModelScope.launch {
        repository.loadAvatar(file)
    }

    private fun errorHandler(errorCode: Int) {
        when(errorCode) {
            105 -> _uiState.value = ProfileUiState.ERROR(R.string.check_your_internet_connection)
            106 -> _uiState.value = ProfileUiState.ERROR(R.string.error)
            else -> _uiState.value = ProfileUiState.ERROR(R.string.unknown_error)
        }
    }
}