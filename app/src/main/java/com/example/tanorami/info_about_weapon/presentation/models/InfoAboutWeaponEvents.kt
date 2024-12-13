package com.example.tanorami.info_about_weapon.presentation.models

import com.example.base.UiEvent

sealed interface InfoAboutWeaponEvents : UiEvent {
    class GetWeaponByID(val idWeapon: Int) : InfoAboutWeaponEvents
    class OnHeroClick(val idHero: Int) : InfoAboutWeaponEvents
    data object OnBackClick : InfoAboutWeaponEvents
}