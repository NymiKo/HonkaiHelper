package com.example.weapons_list.presentation.models

internal sealed interface WeaponsListScreenEvents : com.example.base.UiEvent {
    class SearchTextChanged(val newValue: String) : WeaponsListScreenEvents
    class OnWeaponClick(val idWeapon: Int) : WeaponsListScreenEvents
}