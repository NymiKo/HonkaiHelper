package com.example.tanorami.viewing_users_build.presentation.models

import com.example.core.base.UiEffect

sealed interface ViewingBuildHeroFromUserScreenSideEffects: UiEffect {
    class OnInfoAboutWeaponScreen(val idWeapon: Int): ViewingBuildHeroFromUserScreenSideEffects
    class OnInfoAboutRelicScreen(val idRelic: Int): ViewingBuildHeroFromUserScreenSideEffects
    class OnInfoAboutDecorationScreen(val idDecoration: Int): ViewingBuildHeroFromUserScreenSideEffects
    data object OnCopyUIDClick: ViewingBuildHeroFromUserScreenSideEffects
    data object OnBack: ViewingBuildHeroFromUserScreenSideEffects
}