package com.example.honkaihelper.createteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.R
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
            is NetworkResult.Error -> errorHandler(result.code)
            is NetworkResult.Success -> {
                _uiState.value =
                    CreateTeamUIState.SUCCESS(result.data.map { hero ->
                        ActiveHeroInTeam.toActiveHero(hero)
                    })
            }
        }
    }

    fun saveTeam(heroesList: List<Hero>) = viewModelScope.launch {
        _uiState.value = CreateTeamUIState.LOADING_TEAM_CREATION
        val result = repository.saveTeam(heroesList)
        when(result) {
            is NetworkResult.Error -> errorHandlerTeamCreation(result.code)
            is NetworkResult.Success -> {
                _uiState.value = CreateTeamUIState.SUCCESS_TEAM_CREATION
            }
        }
    }

    private fun errorHandler(errorCode: Int) {
        when(errorCode) {
            105 -> _uiState.value = CreateTeamUIState.ERROR(R.string.check_your_internet_connection)
            else -> _uiState.value = CreateTeamUIState.ERROR(R.string.unknown_error)
        }
    }

    private fun errorHandlerTeamCreation(errorCode: Int) {
        when(errorCode) {
            105 -> _uiState.value = CreateTeamUIState.ERROR_TEAM_CREATION(R.string.check_your_internet_connection)
            400 -> _uiState.value = CreateTeamUIState.ERROR_TEAM_CREATION(R.string.team_already_exists)
            else -> _uiState.value = CreateTeamUIState.ERROR_TEAM_CREATION(R.string.unknown_error)
        }
    }
}