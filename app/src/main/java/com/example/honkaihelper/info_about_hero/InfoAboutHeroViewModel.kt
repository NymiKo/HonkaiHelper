package com.example.honkaihelper.info_about_hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.info_about_hero.data.InfoAboutHeroRepository
import com.example.honkaihelper.info_about_hero.data.model.FullHeroInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoAboutHeroViewModel @Inject constructor(
    private val repository: InfoAboutHeroRepository
): ViewModel() {

    private val _heroInfo = MutableLiveData<FullHeroInfo>()
    val heroInfo: LiveData<FullHeroInfo> = _heroInfo

    fun getHeroInfo(idHero: Int) = viewModelScope.launch {
        _heroInfo.value = repository.getHero(idHero)
    }

}