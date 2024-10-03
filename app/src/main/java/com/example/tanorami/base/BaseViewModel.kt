package com.example.tanorami.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

interface UiState

interface UiEvent

interface UiEffect

abstract class BaseViewModel<S : UiState, E : UiEvent, F : UiEffect>(initialState: S) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    private val _uiEffect: MutableSharedFlow<F?> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    fun uiState(): StateFlow<S> = _uiState.asStateFlow()
    fun uiEffect(): SharedFlow<F?> = _uiEffect.asSharedFlow()

    protected var uiState: S
        get() = _uiState.value
        set(value) {
            _uiState.value = value
        }

    abstract fun onEvent(event: E)

    protected fun sendSideEffect(effect: F) {
        viewModelScope.launch { _uiEffect.emit(effect) }
    }

    fun clearEffect() {
        viewModelScope.launch { _uiEffect.emit(null) }
    }
}