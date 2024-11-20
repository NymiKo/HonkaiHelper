package com.example.heroes_list.heroes.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.base.models.TextField
import com.example.domain.data_store.AppDataStore
import com.example.domain.usecase.GetHeroesListWithBaseInfoUseCase
import com.example.heroes_list.heroes.presentation.models.HeroesListScreenEvents
import com.example.heroes_list.heroes.presentation.models.HeroesListScreenSideEffects
import com.example.heroes_list.heroes.presentation.models.HeroesListScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(
    private val getHeroesListWithBaseInfoUseCase: GetHeroesListWithBaseInfoUseCase,
    private val appDataStore: AppDataStore,
) : BaseViewModel<HeroesListScreenUiState, HeroesListScreenEvents, HeroesListScreenSideEffects>(
    initialState = HeroesListScreenUiState()
) {
    init {
        getHeroesList()
    }

    override fun onEvent(event: HeroesListScreenEvents) {
        when (event) {
            HeroesListScreenEvents.OnSettingsScreen -> sendSideEffect(
                HeroesListScreenSideEffects.OnSettingsScreen
            )
            is HeroesListScreenEvents.OnInfoAboutHeroScreen -> sendSideEffect(
                HeroesListScreenSideEffects.OnInfoAboutHeroScreen(
                    event.idHero
                )
            )

            is HeroesListScreenEvents.ChangeSearchBarFocus -> uiState =
                uiState.copy(
                    searchBarFocus = event.focused
                )

            is HeroesListScreenEvents.SearchTextChanged -> {
                uiState = uiState.copy(
                    searchTextField = TextField(
                        value = event.newValue
                    ),
                    filteredHeroesList = uiState.heroesList.filter { hero ->
                        hero.name.lowercase().contains(event.newValue.lowercase())
                    }
                )
            }
        }
    }

    fun getHeroesList() = viewModelScope.launch {
        appDataStore.versionDB.onEach {
            uiState = uiState.copy(heroesList = getHeroesListWithBaseInfoUseCase.invoke())
        }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }
}