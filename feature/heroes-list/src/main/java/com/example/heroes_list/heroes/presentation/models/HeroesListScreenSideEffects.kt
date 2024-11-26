package com.example.heroes_list.heroes.presentation.models

internal sealed interface HeroesListScreenSideEffects : com.example.base.UiEffect {
    data object OnSettingsScreen : HeroesListScreenSideEffects
    class OnInfoAboutHeroScreen(val idHero: Int) : HeroesListScreenSideEffects
}