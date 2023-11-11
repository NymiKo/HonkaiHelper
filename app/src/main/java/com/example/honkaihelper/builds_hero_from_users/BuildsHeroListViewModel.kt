package com.example.honkaihelper.builds_hero_from_users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.builds_hero_from_users.data.BuildsHeroListRepository
import com.example.honkaihelper.builds_hero_from_users.data.model.FullBuildHeroFromUser
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import kotlinx.coroutines.launch
import javax.inject.Inject

class BuildsHeroListViewModel @Inject constructor(
    private val repository: BuildsHeroListRepository
): ViewModel() {

    private val _buildsHeroList = MutableLiveData<List<FullBuildHeroFromUser>>()
    val buildsHeroList: LiveData<List<FullBuildHeroFromUser>> = _buildsHeroList

    private val _hero = MutableLiveData<HeroWithNameAvatarRarity>()
    val hero: LiveData<HeroWithNameAvatarRarity> = _hero

    fun getBuildsHeroList(idHero: Int) = viewModelScope.launch {
        _buildsHeroList.value = repository.getBuildsHeroList(idHero)
    }

    fun getHero(idHero: Int) = viewModelScope.launch {
        _hero.value = repository.getHero(idHero)
    }
}