package com.example.tanorami.weapons_list.presentation.models

import com.example.core.base.UiEffect

sealed interface WeaponsListScreenSideEffects : UiEffect {
    class OnInfoAboutWeaponScreen(val idWeapon: Int) : WeaponsListScreenSideEffects
}