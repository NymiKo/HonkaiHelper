package com.example.tanorami.createteam.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.base.BaseViewModel
import com.example.data.remote.NetworkResult
import com.example.tanorami.createteam.data.CreateTeamRepository
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.createteam.presentation.models.CreateTeamScreenEvents
import com.example.tanorami.createteam.presentation.models.CreateTeamScreenSideEffects
import com.example.tanorami.createteam.presentation.models.CreateTeamScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateTeamViewModel @Inject constructor(
    private val repository: CreateTeamRepository
) : BaseViewModel<CreateTeamScreenUiState, CreateTeamScreenEvents, CreateTeamScreenSideEffects>(
    initialState = CreateTeamScreenUiState()
) {

    init {
        getHeroesList()
    }

    override fun onEvent(event: CreateTeamScreenEvents) {
        when (event) {
            is CreateTeamScreenEvents.AddHeroInTeam -> addHeroInTeam(event.activeHeroInTeam)
            is CreateTeamScreenEvents.RemoveHeroFromTeam -> removeHeroFromTeam(event.activeHeroInTeam)
            is CreateTeamScreenEvents.GetTeam -> getTeam(idTeam = event.idTeam ?: -1L)
            CreateTeamScreenEvents.DeleteTeam -> deleteTeam()
            CreateTeamScreenEvents.SaveTeam -> saveTeam()
            CreateTeamScreenEvents.OnBack -> sendSideEffect(CreateTeamScreenSideEffects.OnBack)
            is CreateTeamScreenEvents.ChangeVisibilityDialogDeleteTeam -> uiState =
                uiState.copy(dialogDeleteTeamVisibilityState = event.visibility)
        }
    }

    private fun getHeroesList() = viewModelScope.launch {
        uiState = uiState.copy(heroesList = repository.getHeroesList())
    }

    private fun addHeroInTeam(activeHeroInTeam: ActiveHeroInTeam) {
        if (uiState.heroesListInTeam.size < 4) {
            activeHeroInTeam.active = true
            val newList = uiState.heroesListInTeam.toMutableList()
            newList.add(activeHeroInTeam.hero)
            uiState = uiState.copy(heroesListInTeam = newList)
        }
    }

    private fun removeHeroFromTeam(activeHeroInTeam: ActiveHeroInTeam) {
        if (uiState.heroesListInTeam.isNotEmpty()) {
            activeHeroInTeam.active = false
            uiState =
                uiState.copy(heroesListInTeam = uiState.heroesListInTeam.minus(activeHeroInTeam.hero))
        }
    }

    private fun saveTeam() = viewModelScope.launch {
        when (val result = repository.saveTeam(uiState.idTeam, uiState.heroesListInTeam)) {
            is NetworkResult.Error -> {
                val errorMessage = errorHandler(result.code)
                sendSideEffect(CreateTeamScreenSideEffects.ShowToastError(errorMessage))
            }

            is NetworkResult.Success -> {
                sendSideEffect(CreateTeamScreenSideEffects.TeamSaved)
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

    private fun getTeam(idTeam: Long) {
        if (idTeam != -1L) {
            uiState = uiState.copy(isCreateTeamMode = false)
            getTeamFromServer(idTeam)
        } else {
            uiState = uiState.copy(isCreateTeamMode = true)
        }
    }

    private fun getTeamFromServer(idTeam: Long) = viewModelScope.launch {
        uiState = uiState.copy(isLoading = true)
        when (val result = repository.getTeam(idTeam)) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isSuccess = false,
                    isError = true,
                    isCreateTeamMode = false
                )
            }

            is NetworkResult.Success -> {
                val newList = uiState.heroesList.toMutableList()
                result.data.second.forEach {
                    newList[newList.indexOf(ActiveHeroInTeam(it))] = ActiveHeroInTeam(it, true)
                }
                uiState = uiState.copy(
                    idTeam = idTeam,
                    isLoading = false,
                    isSuccess = true,
                    isError = false,
                    uidTeam = result.data.first,
                    heroesList = newList,
                    heroesListInTeam = result.data.second,
                    isCreateTeamMode = false,
                )
            }
        }
    }

    private fun deleteTeam() = viewModelScope.launch {
        when (val result = repository.deleteTeam(uiState.idTeam)) {
            is NetworkResult.Error -> {
                val errorMessage = errorHandler(result.code)
                sendSideEffect(CreateTeamScreenSideEffects.ShowToastError(errorMessage))
            }

            is NetworkResult.Success -> {
                sendSideEffect(CreateTeamScreenSideEffects.TeamDeleted)
                uiState = uiState.copy(dialogDeleteTeamVisibilityState = false)
            }
        }
    }
}