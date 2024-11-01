package com.example.tanorami.create_build_heroes_list.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface CreateBuildHeroesListScreenSideEffects : UiEffect {
    class OnCreateBuildForHeroScreen(val idHero: Int) : CreateBuildHeroesListScreenSideEffects
    data object OnBack : CreateBuildHeroesListScreenSideEffects
}