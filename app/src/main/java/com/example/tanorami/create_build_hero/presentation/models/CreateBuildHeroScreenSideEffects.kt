package com.example.tanorami.create_build_hero.presentation.models

import com.example.tanorami.base.UiEffect

sealed interface CreateBuildHeroScreenSideEffects: UiEffect {
    data object OnBack: CreateBuildHeroScreenSideEffects
}