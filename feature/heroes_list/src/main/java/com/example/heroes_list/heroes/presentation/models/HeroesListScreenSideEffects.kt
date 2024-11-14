package com.example.heroes_list.heroes.presentation.models

import com.example.core.base.UiEffect

sealed interface HeroesListScreenSideEffects : UiEffect {
    data object OnSettingsScreen : HeroesListScreenSideEffects
    class OnInfoAboutHeroScreen(val idHero: Int) : HeroesListScreenSideEffects
}