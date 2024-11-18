package com.example.tanorami.info_about_relic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.relic.RelicModel
import com.example.tanorami.info_about_relic.data.InfoAboutRelicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoAboutRelicViewModel @Inject constructor(
    private val repository: InfoAboutRelicRepository
): ViewModel() {
    private val _relic = MutableStateFlow<RelicModel?>(null)
    val relic: StateFlow<RelicModel?> = _relic

    fun getRelic(idRelic: Int) = viewModelScope.launch {
        _relic.emit(repository.getRelic(idRelic))
    }
}