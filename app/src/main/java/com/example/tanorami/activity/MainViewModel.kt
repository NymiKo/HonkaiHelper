package com.example.tanorami.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data_store.AppDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val dataStore: AppDataStore,
) : ViewModel() {
    private val _showNewYearAnimation: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showNewYearAnimation: StateFlow<Boolean> = _showNewYearAnimation.asStateFlow()

    private val _countSnowflakes: MutableStateFlow<Int> = MutableStateFlow(80)
    val countSnowflakes: StateFlow<Int> = _countSnowflakes.asStateFlow()

    init {
        dataStore.showSnowfallAnimation
            .onEach {
                _showNewYearAnimation.value = it
            }
            .launchIn(viewModelScope)

        dataStore.countSnowflakes
            .onEach {
                _countSnowflakes.value = it.toInt()
            }
            .launchIn(viewModelScope)
    }
}