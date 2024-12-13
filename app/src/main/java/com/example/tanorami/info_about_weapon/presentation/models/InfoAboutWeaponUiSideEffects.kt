package com.example.tanorami.info_about_weapon.presentation.models

import com.example.base.UiEffect

sealed interface InfoAboutWeaponUiSideEffects : UiEffect {
    class OnInfoAboutHeroScreen(val idHero: Int) : InfoAboutWeaponUiSideEffects
    data object OnBackClick : InfoAboutWeaponUiSideEffects
}