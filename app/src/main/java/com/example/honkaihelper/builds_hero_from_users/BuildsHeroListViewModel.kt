package com.example.honkaihelper.builds_hero_from_users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.builds_hero_from_users.data.BuildsHeroListRepository
import com.example.honkaihelper.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.honkaihelper.builds_hero_from_users.di.BuildsHeroListUIState
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import kotlinx.coroutines.launch
import javax.inject.Inject

class BuildsHeroListViewModel @Inject constructor(
    private val repository: BuildsHeroListRepository
): ViewModel() {

    private val _uiState = MutableLiveData<BuildsHeroListUIState<BuildHeroWithUser>>(BuildsHeroListUIState.LOADING)
    val uiState: LiveData<BuildsHeroListUIState<BuildHeroWithUser>> = _uiState

    private val _hero = MutableLiveData<HeroWithNameAvatarRarity>()
    val hero: LiveData<HeroWithNameAvatarRarity> = _hero

    fun getBuildsHeroList(idHero: Int) = viewModelScope.launch {
        _uiState.value = BuildsHeroListUIState.LOADING
        val result = repository.getBuildsHeroList(idHero)
        when(result) {
            is NetworkResult.Error -> {
                _uiState.value = BuildsHeroListUIState.ERROR
            }
            is NetworkResult.Success -> {
                if (result.data.isEmpty()) _uiState.value = BuildsHeroListUIState.EMPTY
                else _uiState.value = BuildsHeroListUIState.SUCCESS(result.data)
            }
        }
    }

    fun getHero(idHero: Int) = viewModelScope.launch {
        _hero.value = repository.getHero(idHero)
    }
}