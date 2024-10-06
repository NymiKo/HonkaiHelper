package com.example.tanorami.info_about_relic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.info_about_hero.data.model.Relic
import com.example.tanorami.info_about_relic.data.RelicInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RelicInfoViewModel @Inject constructor(
    private val repository: RelicInfoRepository
): ViewModel() {
    private val _relic = MutableStateFlow<Relic?>(null)
    val relic: StateFlow<Relic?> = _relic

    fun getRelic(idRelic: Int) = viewModelScope.launch {
        _relic.emit(repository.getRelic(idRelic))
    }
}