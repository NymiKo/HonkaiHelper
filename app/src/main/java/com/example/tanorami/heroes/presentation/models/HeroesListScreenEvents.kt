package com.example.tanorami.heroes.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface HeroesListScreenEvents : UiEvent {
    data object OnSettingsScreen : HeroesListScreenEvents
    data object OnProfileScreen : HeroesListScreenEvents
    class OnInfoAboutHeroScreen(val idHero: Int) : HeroesListScreenEvents
    class ChangeSearchBarFocus(val focused: Boolean) : HeroesListScreenEvents
    class SearchTextChanged(val newValue: String) : HeroesListScreenEvents
}