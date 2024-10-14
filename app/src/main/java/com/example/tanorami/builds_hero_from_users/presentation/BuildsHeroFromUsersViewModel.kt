package com.example.tanorami.builds_hero_from_users.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListRepository
import com.example.tanorami.builds_hero_from_users.presentation.models.BuildsHeroFromUsersScreenEvents
import com.example.tanorami.builds_hero_from_users.presentation.models.BuildsHeroFromUsersScreenSideEffects
import com.example.tanorami.builds_hero_from_users.presentation.models.BuildsHeroFromUsersScreenUiState
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.AppDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class BuildsHeroFromUsersViewModel @Inject constructor(
    private val repository: BuildsHeroListRepository,
    private val userDataStore: AppDataStore,
): BaseViewModel<BuildsHeroFromUsersScreenUiState, BuildsHeroFromUsersScreenEvents, BuildsHeroFromUsersScreenSideEffects>(
    initialState = BuildsHeroFromUsersScreenUiState()
) {

    init {
        userDataStore.tokenUser
            .onEach { uiState = uiState.copy(tokenUser = it) }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    override fun onEvent(event: BuildsHeroFromUsersScreenEvents) {
        when(event) {
            is BuildsHeroFromUsersScreenEvents.GetBuildsHeroList -> getBuildsHeroList(idHero = event.idHero)

            is BuildsHeroFromUsersScreenEvents.OnViewingBuildHeroFromUserScreen -> sendSideEffect(BuildsHeroFromUsersScreenSideEffects.OnViewingBuildHeroFromUserScreen(event.idBuild))
            BuildsHeroFromUsersScreenEvents.OnCreateBuildHeroScreen -> sendSideEffect(BuildsHeroFromUsersScreenSideEffects.OnCreateBuildHeroScreen)
            BuildsHeroFromUsersScreenEvents.OnBack -> sendSideEffect(BuildsHeroFromUsersScreenSideEffects.OnBack)
        }
    }

    private fun getBuildsHeroList(idHero: Int) = viewModelScope.launch {
        getHero(idHero)

        when(val result = repository.getBuildsHeroList(idHero)) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(isSuccess = false, isError = true)
            }
            is NetworkResult.Success -> {
                uiState = uiState.copy(isSuccess = true, isError = false, buildsList = result.data)
            }
        }
    }

    private fun getHero(idHero: Int) = viewModelScope.launch {
        uiState = uiState.copy(hero = repository.getHero(idHero))
    }
}