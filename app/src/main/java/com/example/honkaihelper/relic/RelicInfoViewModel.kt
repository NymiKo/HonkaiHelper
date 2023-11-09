package com.example.honkaihelper.relic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.info_about_hero.data.model.Relic
import com.example.honkaihelper.relic.data.RelicInfoRepository
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