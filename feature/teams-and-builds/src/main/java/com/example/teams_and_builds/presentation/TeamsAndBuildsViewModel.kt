package com.example.teams_and_builds.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.base.BaseViewModel
import com.example.domain.util.NetworkResult
import com.example.teams_and_builds.data.TeamsAndBuildsRepository
import com.example.teams_and_builds.presentation.models.TeamsAndBuildsScreenEvents
import com.example.teams_and_builds.presentation.models.TeamsAndBuildsScreenSideEffects
import com.example.teams_and_builds.presentation.models.TeamsAndBuildsScreenSideEffects.*
import com.example.teams_and_builds.presentation.models.TeamsAndBuildsScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class TeamsAndBuildsViewModel @Inject constructor(
    private val repository: TeamsAndBuildsRepository,
) : BaseViewModel<TeamsAndBuildsScreenUiState, TeamsAndBuildsScreenEvents, TeamsAndBuildsScreenSideEffects>(
    initialState = TeamsAndBuildsScreenUiState()
) {
    init {
        getBuildsListFromUsers()
        getTeamsListFromUsers()
    }

    override fun onEvent(event: TeamsAndBuildsScreenEvents) {
        when (event) {
            is TeamsAndBuildsScreenEvents.OnViewingBuildHeroFromUserScreen -> sendSideEffect(
                OnViewingBuildHeroFromUserScreen(event.idBuild)
            )

            is TeamsAndBuildsScreenEvents.ChangeActivePage -> uiState =
                uiState.copy(activePageIndex = event.activePageIndex)

            TeamsAndBuildsScreenEvents.RefreshBuildsList -> getBuildsListFromUsers()
            TeamsAndBuildsScreenEvents.RefreshTeamsList -> getTeamsListFromUsers()
            is TeamsAndBuildsScreenEvents.OnDoneButtonTeamsFilersClick -> getTeamsListFromUsers()
            is TeamsAndBuildsScreenEvents.UidTeamFieldChanged -> uiState =
                uiState.copy(uidTeamField = uiState.uidTeamField.copy(value = event.newValue))

            is TeamsAndBuildsScreenEvents.OnDoneButtonBuildsFilersClick -> getBuildsListFromUsers()
            is TeamsAndBuildsScreenEvents.UidBuildFieldChanged -> uiState =
                uiState.copy(uidBuildField = uiState.uidBuildField.copy(value = event.newValue))
        }
    }

    private fun getTeamsListFromUsers() = viewModelScope.launch {
        uiState = uiState.copy(refreshingTeamsList = true)
        val result = repository.getTeamsListFromUsers(
            uiState.uidTeamField.value,
            uiState.idHeroInTeamFilters
        ).cachedIn(viewModelScope)
        uiState = uiState.copy(teamsList = result, refreshingTeamsList = false)
    }

    private fun getBuildsListFromUsers() = viewModelScope.launch {
        uiState = uiState.copy(refreshingBuildsList = true)
        when (val result = repository.getBuildsListFromUsers(
            uiState.uidBuildField.value,
            uiState.idHeroInBuildFilters
        )) {
            is NetworkResult.Error -> {

            }

            is NetworkResult.Success -> {
                uiState = uiState.copy(buildsList = result.data, refreshingBuildsList = false)
            }
        }
    }
}