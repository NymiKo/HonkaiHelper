package com.example.honkaihelper.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.models.TeamHero
import com.example.honkaihelper.teams.TeamsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(
    private val repository: HeroesListRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<HeroesUiState<List<Hero>>>(HeroesUiState.IDLE)
    val uiState: LiveData<HeroesUiState<List<Hero>>> = _uiState

    init {
        getHeroesList()
    }

    private fun getHeroesList() = viewModelScope.launch {
        _uiState.value = HeroesUiState.LOADING
        val result = repository.getHeroesList()
        _uiState.value = HeroesUiState.SUCCESS(result)
    }
}