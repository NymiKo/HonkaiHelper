package com.example.tanorami.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.activity.data.MainRepository
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.data_store.AppDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dataStore: AppDataStore,
): ViewModel() {

    private val _uiState = MutableLiveData<MainUiState>()
    val uiState: LiveData<MainUiState> = _uiState

    private var _avatar = MutableStateFlow("")
    val avatar: StateFlow<String> = _avatar.asStateFlow()

    init {
        dataStore.versionDB
            .onEach { currentVersion ->
                getVersionDB(currentVersion)
            }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)

        viewModelScope.launch {
            val token = dataStore.tokenUser.first()
            if (token.isNotEmpty()) {
                when (val result = repository.getAvatar()) {
                    is NetworkResult.Error -> {

                    }

                    is NetworkResult.Success -> {
                        _avatar.value = result.data
                    }
                }
            } else {
                _avatar.value = ""
            }
        }
    }

    private fun getVersionDB(currentVersion: String) = viewModelScope.launch {
        when(val result = repository.getRemoteVersionDB()) {
            is NetworkResult.Error -> {
                _uiState.value = MainUiState.ERROR
            }
            is NetworkResult.Success -> {
                val version = result.data
                if (version != currentVersion) {
                    _uiState.value = MainUiState.DATA_NOT_CORRECTLY(version)
                }
            }
        }
    }
}