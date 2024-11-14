package com.example.tanorami.info_about_hero.presentation.models

import com.example.core.base.UiEvent

sealed interface InfoAboutHeroScreenEvents : UiEvent {
    class GetHeroInfo(val idHero: Int): InfoAboutHeroScreenEvents
    data object OnBaseBuildHeroScreen: InfoAboutHeroScreenEvents
    data object OnTeamsListScreen: InfoAboutHeroScreenEvents
    data object OnBack: InfoAboutHeroScreenEvents
}