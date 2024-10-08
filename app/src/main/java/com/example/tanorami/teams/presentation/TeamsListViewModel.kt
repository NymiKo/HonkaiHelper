package com.example.tanorami.teams.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.UserDataStore
import com.example.tanorami.teams.data.TeamsListRepository
import com.example.tanorami.teams.presentation.models.TeamsListScreenEvents
import com.example.tanorami.teams.presentation.models.TeamsListScreenSideEffects
import com.example.tanorami.teams.presentation.models.TeamsListScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamsListViewModel @Inject constructor(
    private val repository: TeamsListRepository,
    private val userDataStore: UserDataStore,
) : BaseViewModel<TeamsListScreenUiState, TeamsListScreenEvents, TeamsListScreenSideEffects>(
    initialState = TeamsListScreenUiState()
) {

    override fun onEvent(event: TeamsListScreenEvents) {
        when(event) {
            is TeamsListScreenEvents.GetNameHero -> getNameHero(event.idHero)
            is TeamsListScreenEvents.GetTeamsList -> getTeamsList(event.idHero, event.uid)
            TeamsListScreenEvents.OnBack -> {
                sendSideEffect(TeamsListScreenSideEffects.OnBack)
            }

            TeamsListScreenEvents.GetToken -> getToken()
            TeamsListScreenEvents.OnCreateTeamScreen -> sendSideEffect(TeamsListScreenSideEffects.OnCreateTeamScreen)
        }
    }

    private fun getToken() = viewModelScope.launch {
        userDataStore.tokenUser.collect {
            uiState = uiState.copy(tokenUser = it)
        }
    }

    private fun getNameHero(idHero: Int) = viewModelScope.launch {
        if (idHero != -1) {
            uiState = uiState.copy(nameHero = repository.getNameHero(idHero))
        }
    }

    private fun getTeamsList(idHero: Int, uid: String?) = viewModelScope.launch {
        val result = if (uid == "") {
            repository.getTeamsListByID(idHero)
        } else {
            repository.getTeamsListByUID(uid!!)
        }

        when (result) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isError = true
                )
            }
            is NetworkResult.Success -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isError = false,
                    teamsList = result.data,
                    uid = uid
                )
            }
        }
    }
}