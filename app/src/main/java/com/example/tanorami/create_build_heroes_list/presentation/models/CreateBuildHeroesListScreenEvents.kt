package com.example.tanorami.create_build_heroes_list.presentation.models

import com.example.core.base.UiEvent

sealed interface CreateBuildHeroesListScreenEvents : UiEvent {
    data object GetHeroesList : CreateBuildHeroesListScreenEvents
    class OnCreateBuildForHeroScreen(val idHero: Int) : CreateBuildHeroesListScreenEvents
    data object OnBack : CreateBuildHeroesListScreenEvents
}