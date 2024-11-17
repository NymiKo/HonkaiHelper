package com.example.tanorami.info_about_relic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.info_about_relic.data.InfoAboutRelicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoAboutRelicViewModel @Inject constructor(
    private val repository: InfoAboutRelicRepository
): ViewModel() {
    private val _relic = MutableStateFlow<com.example.domain.repository.relic.Relic?>(null)
    val relic: StateFlow<com.example.domain.repository.relic.Relic?> = _relic

    fun getRelic(idRelic: Int) = viewModelScope.launch {
        _relic.emit(repository.getRelic(idRelic))
    }
}