package com.example.tanorami.viewing_users_build.presentation.models

import com.example.base.UiEvent

sealed interface ViewingBuildHeroFromUserScreenEvents : UiEvent {
    class GetHeroBuild(val idBuild: Long) : ViewingBuildHeroFromUserScreenEvents
    data object OnInfoAboutWeaponScreen: ViewingBuildHeroFromUserScreenEvents
    data object OnTwoPartRelicClick: ViewingBuildHeroFromUserScreenEvents
    data object OnFourPartRelicClick: ViewingBuildHeroFromUserScreenEvents
    data object OnInfoAboutDecorationScreen: ViewingBuildHeroFromUserScreenEvents
    data object OnCopyUIDClick: ViewingBuildHeroFromUserScreenEvents
    data object OnBack: ViewingBuildHeroFromUserScreenEvents
}