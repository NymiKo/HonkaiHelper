package com.example.tanorami.base_build_hero.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface BaseBuildHeroScreenEvents : UiEvent {
    class GetFullBaseBuildHero(val idHero: Int): BaseBuildHeroScreenEvents
    data object OnBuildsHeroFromUsersScreen: BaseBuildHeroScreenEvents
    class OnInfoAboutWeaponScreen(val idWeapon: Int): BaseBuildHeroScreenEvents
    class OnInfoAboutRelicScreen(val idRelic: Int): BaseBuildHeroScreenEvents
    class OnInfoAboutDecorationScreen(val idDecoration: Int): BaseBuildHeroScreenEvents
    data object OnBack: BaseBuildHeroScreenEvents
}