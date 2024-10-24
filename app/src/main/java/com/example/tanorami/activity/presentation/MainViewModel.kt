package com.example.tanorami.activity.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.activity.data.MainRepository
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.data_store.AppDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dataStore: AppDataStore,
) : ViewModel() {
    private var _avatar = MutableStateFlow("")
    val avatar: StateFlow<String> = _avatar.asStateFlow()

    init {
        viewModelScope.launch {
            val token = dataStore.tokenUser.first()
            if (token.isNotEmpty()) {
                when (val result = repository.getAvatar()) {
                    is NetworkResult.Error -> {
                        _avatar.value = ""
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
}