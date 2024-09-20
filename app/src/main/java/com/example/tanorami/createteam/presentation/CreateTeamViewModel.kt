package com.example.tanorami.createteam.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.createteam.data.CreateTeamRepository
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.data.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateTeamViewModel @Inject constructor(
    private val repository: CreateTeamRepository
) : ViewModel() {

    var uiState by mutableStateOf(CreateTeamScreenUiState())

    init {
        getHeroesList()
    }

    fun onEvent(event: CreateTeamScreenEvents) {
        when(event) {
            is CreateTeamScreenEvents.AddHeroInTeam -> addHeroInTeam(event.activeHeroInTeam)
            is CreateTeamScreenEvents.RemoveHeroFromTeam -> removeHeroFromTeam(event.activeHeroInTeam)
            is CreateTeamScreenEvents.GetTeam -> getTeam(idTeam = event.idTeam)
        }
    }

    private fun getHeroesList() = viewModelScope.launch {
        uiState = uiState.copy(heroesList = repository.getHeroesList())
    }

    private fun addHeroInTeam(activeHeroInTeam: ActiveHeroInTeam) {
        if (uiState.heroesListInTeam.size < 4) {
            activeHeroInTeam.active = true
            val currentList = uiState.heroesListInTeam
            val newList = currentList.toMutableList()
            newList.add(activeHeroInTeam.hero)
            uiState = uiState.copy(heroesListInTeam = newList)
        }
    }

    private fun removeHeroFromTeam(activeHeroInTeam: ActiveHeroInTeam) {
        if (uiState.heroesListInTeam.isNotEmpty()) {
            uiState = uiState.copy(heroesListInTeam = uiState.heroesListInTeam.minus(activeHeroInTeam.hero))
            activeHeroInTeam.active = false
        }
    }

    private fun saveTeam(idTeam: Long) = viewModelScope.launch {
        when(val result = repository.saveTeam(idTeam, uiState.heroesListInTeam)) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(isSuccess = false, isError = true, message = errorHandler(result.code))
            }
            is NetworkResult.Success -> {
                uiState = uiState.copy(isSuccess = true, isError = false)
            }
        }
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            400 -> R.string.team_already_exists
            503 -> R.string.error_save_in_server
            else -> R.string.error
        }
    }

    private fun getTeam(idTeam: Long) = viewModelScope.launch {
        if (idTeam != -1L) getTeamFromServer(idTeam)
    }

    private fun getTeamFromServer(idTeam: Long) = viewModelScope.launch {
        uiState = uiState.copy(isCreateTeamMode = false)
        when(val result = repository.getTeam(idTeam)) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(isSuccess = false, isError = true, message = errorHandler(result.code))
            }
            is NetworkResult.Success -> {
                result.data.second.forEach {
                    val list = uiState.heroesList
                    val newList = list.toMutableList()
                    newList[newList.indexOf(ActiveHeroInTeam(it))] = ActiveHeroInTeam(it, true)
                    uiState = uiState.copy(heroesList = newList)
                }
                uiState = uiState.copy(
                    isSuccess = true,
                    isError = false,
                    uidTeam = result.data.first,
                    heroesListInTeam = result.data.second,
                )
            }
        }
    }

    private fun deleteTeam(idTeam: Long) = viewModelScope.launch {
        when(val result = repository.deleteTeam(idTeam)) {
            is NetworkResult.Error -> errorHandler(result.code)
            is NetworkResult.Success -> {

            }
        }
    }
}