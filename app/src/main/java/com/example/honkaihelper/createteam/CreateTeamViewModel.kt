package com.example.honkaihelper.createteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.createteam.data.CreateTeamRepository
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.models.ActiveHeroInTeam
import com.example.honkaihelper.models.Hero
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateTeamViewModel @Inject constructor(
    private val repository: CreateTeamRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<CreateTeamUIState<Any>>(CreateTeamUIState.LOADING)
    val uiState: LiveData<CreateTeamUIState<Any>> = _uiState

    init {
        getHeroesList()
    }

    fun getHeroesList() = viewModelScope.launch {
        _uiState.value = CreateTeamUIState.LOADING
        val result = repository.getHeroesList()
        when (result) {
            is NetworkResult.Error -> _uiState.value = CreateTeamUIState.ERROR
            is NetworkResult.Success -> _uiState.value =
                CreateTeamUIState.SUCCESS(result.data.map { hero ->
                    ActiveHeroInTeam.toActiveHero(hero)
                })
        }
    }

    fun saveTeam(heroesList: List<Hero>) = viewModelScope.launch {
        // TODO: Добавить изменение состояние при успешном сохранении или провале
        repository.saveTeam(heroesList)
    }
}