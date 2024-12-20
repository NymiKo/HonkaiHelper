package com.example.teams_and_builds.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.domain.util.NetworkResult
import com.example.teams_and_builds.data.TeamsAndBuildsRepository
import com.example.teams_and_builds.presentation.models.TeamsAndBuildsScreenEvents
import com.example.teams_and_builds.presentation.models.TeamsAndBuildsScreenSideEffects
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
                TeamsAndBuildsScreenSideEffects.OnViewingBuildHeroFromUserScreen(event.idBuild)
            )

            is TeamsAndBuildsScreenEvents.SearchTextChanged -> uiState =
                uiState.copy(searchText = uiState.searchText.copy(value = event.newValue))

            is TeamsAndBuildsScreenEvents.ChangeActivePage -> uiState =
                uiState.copy(activePageIndex = event.activePageIndex)

            TeamsAndBuildsScreenEvents.RefreshBuildsList -> getBuildsListFromUsers()
            TeamsAndBuildsScreenEvents.RefreshTeamsList -> getTeamsListFromUsers()
        }
    }

    private fun getTeamsListFromUsers() = viewModelScope.launch {
        uiState = uiState.copy(refreshingTeamsList = true)
        when (val result = repository.getTeamsListFromUsers()) {
            is NetworkResult.Error -> {

            }
            is NetworkResult.Success -> {
                uiState = uiState.copy(
                    teamsList = result.data,
                    refreshingTeamsList = false
                )
            }
        }
    }

    private fun getBuildsListFromUsers() = viewModelScope.launch {
        uiState = uiState.copy(refreshingBuildsList = true)
        when (val result = repository.getBuildsListFromUsers()) {
            is NetworkResult.Error -> {

            }

            is NetworkResult.Success -> {
                uiState = uiState.copy(buildsList = result.data, refreshingBuildsList = false)
            }
        }
    }
}