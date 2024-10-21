package com.example.tanorami.heroes.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface HeroesListScreenSideEffects : UiEffect {
    data object OnSettingsScreen : HeroesListScreenSideEffects
    data object OnProfileScreen : HeroesListScreenSideEffects
    class OnInfoAboutHeroScreen(val idHero: Int) : HeroesListScreenSideEffects
}