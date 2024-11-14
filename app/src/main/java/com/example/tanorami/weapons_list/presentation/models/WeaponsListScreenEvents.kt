package com.example.tanorami.weapons_list.presentation.models

import com.example.core.base.UiEvent

sealed interface WeaponsListScreenEvents : UiEvent {
    class SearchTextChanged(val newValue: String) : WeaponsListScreenEvents
    class OnWeaponClick(val idWeapon: Int) : WeaponsListScreenEvents
}