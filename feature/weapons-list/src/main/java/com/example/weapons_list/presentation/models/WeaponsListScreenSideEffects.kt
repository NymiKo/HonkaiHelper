package com.example.weapons_list.presentation.models

import com.example.base.UiEffect

internal sealed interface WeaponsListScreenSideEffects : UiEffect {
    class OnInfoAboutWeaponScreen(val idWeapon: Int) : WeaponsListScreenSideEffects
}