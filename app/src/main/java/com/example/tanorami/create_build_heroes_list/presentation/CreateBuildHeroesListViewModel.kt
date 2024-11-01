package com.example.tanorami.create_build_heroes_list.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.create_build_heroes_list.presentation.models.CreateBuildHeroesListScreenEvents
import com.example.tanorami.create_build_heroes_list.presentation.models.CreateBuildHeroesListScreenSideEffects
import com.example.tanorami.create_build_heroes_list.presentation.models.CreateBuildHeroesListScreenUiState
import com.example.tanorami.heroes.data.HeroesListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateBuildHeroesListViewModel @Inject constructor(
    private val repository: HeroesListRepository,
) : BaseViewModel<CreateBuildHeroesListScreenUiState, CreateBuildHeroesListScreenEvents, CreateBuildHeroesListScreenSideEffects>(
    initialState = CreateBuildHeroesListScreenUiState()
) {
    override fun onEvent(event: CreateBuildHeroesListScreenEvents) {
        when (event) {
            CreateBuildHeroesListScreenEvents.GetHeroesList -> getHeroesList()
            CreateBuildHeroesListScreenEvents.OnBack -> sendSideEffect(
                CreateBuildHeroesListScreenSideEffects.OnBack
            )

            is CreateBuildHeroesListScreenEvents.OnCreateBuildForHeroScreen -> sendSideEffect(
                CreateBuildHeroesListScreenSideEffects.OnCreateBuildForHeroScreen(event.idHero)
            )
        }
    }

    private fun getHeroesList() = viewModelScope.launch {
        uiState = uiState.copy(heroesList = repository.getHeroesList())
    }
}