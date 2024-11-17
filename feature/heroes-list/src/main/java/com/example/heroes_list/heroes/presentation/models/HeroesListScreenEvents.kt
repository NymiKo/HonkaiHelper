package com.example.heroes_list.heroes.presentation.models

import com.example.core.base.UiEvent

sealed interface HeroesListScreenEvents : UiEvent {
    data object OnSettingsScreen : HeroesListScreenEvents
    class OnInfoAboutHeroScreen(val idHero: Int) : HeroesListScreenEvents
    class ChangeSearchBarFocus(val focused: Boolean) : HeroesListScreenEvents
    class SearchTextChanged(val newValue: String) : HeroesListScreenEvents
}