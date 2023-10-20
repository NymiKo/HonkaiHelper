package com.example.honkaihelper.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.activity.data.MainRepository
import com.example.honkaihelper.data.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _uiState = MutableLiveData<MainUiState>()
    val uiState: LiveData<MainUiState> = _uiState

    fun getVersionDB(currentVersion: String) = viewModelScope.launch {
        val result = repository.getRemoteVersionDB()
        when(result) {
            is NetworkResult.Error -> {
                _uiState.value = MainUiState.ERROR
            }
            is NetworkResult.Success -> {
                val version = result.data
                if (version == currentVersion) {
                    _uiState.value = MainUiState.DATA_CORRECTLY
                } else {
                    _uiState.value = MainUiState.DATA_NOT_CORRECTLY(version)
                }
            }
        }
    }
}