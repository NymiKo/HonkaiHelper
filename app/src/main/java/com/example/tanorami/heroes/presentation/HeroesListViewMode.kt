package com.example.tanorami.heroes.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.data_store.AppDataStore
import com.example.tanorami.heroes.data.HeroesListRepository
import com.example.tanorami.heroes.presentation.models.HeroesListScreenEvents
import com.example.tanorami.heroes.presentation.models.HeroesListScreenSideEffects
import com.example.tanorami.heroes.presentation.models.HeroesListScreenUiState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesListViewMode @Inject constructor(
    private val repository: HeroesListRepository,
    private val appDataStore: AppDataStore,
) : BaseViewModel<HeroesListScreenUiState, HeroesListScreenEvents, HeroesListScreenSideEffects>(
    initialState = HeroesListScreenUiState()
) {

    init {
        getAvatar()
        getHeroesList()
    }

    override fun onEvent(event: HeroesListScreenEvents) {
        when (event) {
            HeroesListScreenEvents.OnSettingsScreen -> sendSideEffect(HeroesListScreenSideEffects.OnSettingsScreen)
            HeroesListScreenEvents.OnProfileScreen -> sendSideEffect(HeroesListScreenSideEffects.OnProfileScreen)
            is HeroesListScreenEvents.OnInfoAboutHeroScreen -> sendSideEffect(
                HeroesListScreenSideEffects.OnInfoAboutHeroScreen(event.idHero)
            )

            is HeroesListScreenEvents.ChangeSearchBarFocus -> uiState =
                uiState.copy(searchBarFocus = event.focused)

            is HeroesListScreenEvents.SearchTextChanged -> {
                uiState = uiState.copy(
                    searchTextField = uiState.searchTextField.copy(value = event.newValue),
                    filteredHeroesList = uiState.heroesList.filter { hero ->
                        hero.name.lowercase().contains(event.newValue.lowercase())
                    }
                )
            }
        }
    }

    fun getHeroesList() = viewModelScope.launch {
        uiState = uiState.copy(heroesList = repository.getHeroesList())
    }

    private fun getAvatar() = viewModelScope.launch {
        val token = appDataStore.tokenUser.first()

        if (token.isNotEmpty()) {
            when (val result = repository.getAvatar()) {
                is NetworkResult.Error -> {

                }

                is NetworkResult.Success -> {
                    uiState = uiState.copy(userAvatar = result.data)
                }
            }
        } else {
            uiState = uiState.copy(userAvatar = "")
        }
    }
}