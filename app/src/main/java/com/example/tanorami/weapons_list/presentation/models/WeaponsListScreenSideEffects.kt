package com.example.tanorami.weapons_list.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface WeaponsListScreenSideEffects : UiEffect {
    class OnInfoAboutWeaponScreen(val idWeapon: Int) : WeaponsListScreenSideEffects
}