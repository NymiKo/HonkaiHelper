package com.example.honkaihelper.base_build_hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.honkaihelper.base_build_hero.data.BaseBuildHeroRepository
import com.example.honkaihelper.base_build_hero.data.model.HeroWithPathAndElement
import javax.inject.Inject

class BaseBuildHeroViewModel @Inject constructor(
    private val repository: BaseBuildHeroRepository
): ViewModel() {

    private val _heroInfo = MutableLiveData<HeroWithPathAndElement>()
    val heroInfo: LiveData<HeroWithPathAndElement> = _heroInfo

}