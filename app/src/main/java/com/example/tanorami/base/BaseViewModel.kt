package com.example.tanorami.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State : Any, Event>(initialState: State) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)

    fun uiState(): StateFlow<State> = _uiState.asStateFlow()

    protected var uiState: State
        get() = _uiState.value
        set(value) {
            _uiState.value = value
        }

    abstract fun onEvent(event: Event)
}