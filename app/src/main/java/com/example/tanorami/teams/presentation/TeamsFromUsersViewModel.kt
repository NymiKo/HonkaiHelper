package com.example.tanorami.teams.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.tanorami.teams.data.TeamsFromUsersRepository
import com.example.tanorami.teams.presentation.models.TeamsFromUsersScreenEvents
import com.example.tanorami.teams.presentation.models.TeamsFromUsersScreenSideEffects
import com.example.tanorami.teams.presentation.models.TeamsFromUsersScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamsFromUsersViewModel @Inject constructor(
    private val repository: TeamsFromUsersRepository,
) : BaseViewModel<TeamsFromUsersScreenUiState, TeamsFromUsersScreenEvents, TeamsFromUsersScreenSideEffects>(
    initialState = TeamsFromUsersScreenUiState()
) {
    override fun onEvent(event: TeamsFromUsersScreenEvents) {
        when (event) {
            is TeamsFromUsersScreenEvents.GetTeamsFromUsers -> {
                getNameHero(event.idHero)
                getTeamsList(event.idHero)
            }

            TeamsFromUsersScreenEvents.OnBack -> {
                sendSideEffect(TeamsFromUsersScreenSideEffects.OnBack)
            }

            TeamsFromUsersScreenEvents.RefreshTeamsList -> getTeamsList(uiState.idHero!!)
        }
    }

    private fun getNameHero(idHero: Int) = viewModelScope.launch {
        uiState = uiState.copy(nameHero = repository.getNameHero(idHero), idHero = idHero)
    }

    private fun getTeamsList(idHero: Int) = viewModelScope.launch {
        uiState = uiState.copy(refreshingTeamsList = true)
        when (val result = repository.getTeamsListByID(idHero)) {
            is com.example.data.remote.NetworkResult.Error -> {
                uiState = uiState.copy(
                    isError = true,
                    refreshingTeamsList = false,
                )
            }

            is com.example.data.remote.NetworkResult.Success -> {
                uiState = uiState.copy(
                    isError = false,
                    refreshingTeamsList = false,
                    teamsList = result.data,
                )
            }
        }
    }
}