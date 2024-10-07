package com.example.tanorami.info_about_hero.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface InfoAboutHeroScreenSideEffects : UiEffect {
    class OnBaseBuildHeroScreen(val idHero: Int): InfoAboutHeroScreenSideEffects
    class OnTeamsListScreen(val idHero: Int): InfoAboutHeroScreenSideEffects
    data object OnBack: InfoAboutHeroScreenSideEffects
}