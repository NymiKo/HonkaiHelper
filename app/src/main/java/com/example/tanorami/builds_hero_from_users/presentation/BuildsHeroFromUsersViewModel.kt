package com.example.tanorami.builds_hero_from_users.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListRepository
import com.example.tanorami.builds_hero_from_users.presentation.models.BuildsHeroFromUsersScreenEvents
import com.example.tanorami.builds_hero_from_users.presentation.models.BuildsHeroFromUsersScreenSideEffects
import com.example.tanorami.builds_hero_from_users.presentation.models.BuildsHeroFromUsersScreenUiState
import com.example.tanorami.core.data.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class BuildsHeroFromUsersViewModel @Inject constructor(
    private val repository: BuildsHeroListRepository,
): BaseViewModel<BuildsHeroFromUsersScreenUiState, BuildsHeroFromUsersScreenEvents, BuildsHeroFromUsersScreenSideEffects>(
    initialState = BuildsHeroFromUsersScreenUiState()
) {

    override fun onEvent(event: BuildsHeroFromUsersScreenEvents) {
        when(event) {
            is BuildsHeroFromUsersScreenEvents.GetBuildsHeroList -> {
                getHero(event.idHero)
                getBuildsHeroList(idHero = event.idHero)
            }
            is BuildsHeroFromUsersScreenEvents.OnViewingBuildHeroFromUserScreen -> sendSideEffect(BuildsHeroFromUsersScreenSideEffects.OnViewingBuildHeroFromUserScreen(event.idBuild))
            BuildsHeroFromUsersScreenEvents.OnBack -> sendSideEffect(BuildsHeroFromUsersScreenSideEffects.OnBack)
        }
    }

    private fun getBuildsHeroList(idHero: Int) = viewModelScope.launch {
        when (val result = repository.getBuildsHeroListByIdHero(idHero)) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(isSuccess = false, isError = true)
            }
            is NetworkResult.Success -> {
                uiState = uiState.copy(isSuccess = true, isError = false, buildsList = result.data)
            }
        }
    }

    private fun getHero(idHero: Int) = viewModelScope.launch {
        val hero = repository.getHero(idHero)
        uiState = uiState.copy(hero = hero)
    }
}