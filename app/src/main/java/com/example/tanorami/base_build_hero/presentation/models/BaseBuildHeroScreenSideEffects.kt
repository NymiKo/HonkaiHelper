package com.example.tanorami.base_build_hero.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface BaseBuildHeroScreenSideEffects : UiEffect {
    class OnBuildsHeroFromUsersScreen(val idHero: Int): BaseBuildHeroScreenSideEffects
    class OnInfoAboutWeaponScreen(val idWeapon: Int): BaseBuildHeroScreenSideEffects
    class OnInfoAboutRelicScreen(val idRelic: Int): BaseBuildHeroScreenSideEffects
    class OnInfoAboutDecorationScreen(val idDecoration: Int): BaseBuildHeroScreenSideEffects
    data object OnBack: BaseBuildHeroScreenSideEffects
}