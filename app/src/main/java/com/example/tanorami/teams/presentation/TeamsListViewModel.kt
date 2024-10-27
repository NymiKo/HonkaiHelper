package com.example.tanorami.teams.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.data_store.AppDataStore
import com.example.tanorami.teams.data.TeamsListRepository
import com.example.tanorami.teams.presentation.models.TeamsListScreenEvents
import com.example.tanorami.teams.presentation.models.TeamsListScreenSideEffects
import com.example.tanorami.teams.presentation.models.TeamsListScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamsListViewModel @Inject constructor(
    private val repository: TeamsListRepository,
    private val appDataStore: AppDataStore,
) : BaseViewModel<TeamsListScreenUiState, TeamsListScreenEvents, TeamsListScreenSideEffects>(
    initialState = TeamsListScreenUiState()
) {
    init {
        appDataStore.tokenUser
            .onEach { uiState = uiState.copy(tokenUser = it) }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    override fun onEvent(event: TeamsListScreenEvents) {
        when(event) {
            is TeamsListScreenEvents.GetNameHero -> getNameHero(event.idHero)
            is TeamsListScreenEvents.GetTeamsList -> getTeamsList(event.idHero, event.uid)
            TeamsListScreenEvents.OnBack -> {
                sendSideEffect(TeamsListScreenSideEffects.OnBack)
            }

            TeamsListScreenEvents.OnCreateTeamScreen -> sendSideEffect(TeamsListScreenSideEffects.OnCreateTeamScreen)
        }
    }

    private fun getNameHero(idHero: Int) = viewModelScope.launch {
        if (idHero != -1) {
            uiState = uiState.copy(nameHero = repository.getNameHero(idHero))
        }
    }

    private fun getTeamsList(idHero: Int, uid: String?) = viewModelScope.launch {
        val result = when {
            idHero != -1 -> repository.getTeamsListByID(idHero)
            else -> repository.getTeamsList()
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
                    uid = uid ?: ""
                )
            }
        }
    }
}