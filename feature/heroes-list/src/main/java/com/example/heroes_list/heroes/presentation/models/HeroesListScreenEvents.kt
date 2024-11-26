package com.example.heroes_list.heroes.presentation.models

internal sealed interface HeroesListScreenEvents : com.example.base.UiEvent {
    data object OnSettingsScreen : HeroesListScreenEvents
    class OnInfoAboutHeroScreen(val idHero: Int) : HeroesListScreenEvents
    class ChangeSearchBarFocus(val focused: Boolean) : HeroesListScreenEvents
    class SearchTextChanged(val newValue: String) : HeroesListScreenEvents
}