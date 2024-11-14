package com.example.tanorami.create_build_hero.presentation.models

import com.example.core.base.UiEffect

sealed interface CreateBuildHeroScreenSideEffects: UiEffect {
    data object OnBack: CreateBuildHeroScreenSideEffects
    data object OnMainScreen : CreateBuildHeroScreenSideEffects
}