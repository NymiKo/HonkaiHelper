package com.example.tanorami.info_about_relic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.info_about_hero.data.model.Relic
import com.example.tanorami.info_about_relic.data.RelicInfoRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RelicInfoViewModel @Inject constructor(
    private val repository: RelicInfoRepository
): ViewModel() {
    private val _relic = MutableLiveData<Relic>()
    val relic: LiveData<Relic> = _relic

    fun getRelic(idRelic: Int) = viewModelScope.launch {
        _relic.value = repository.getRelic(idRelic)
    }
}