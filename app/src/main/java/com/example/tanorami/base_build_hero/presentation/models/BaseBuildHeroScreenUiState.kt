package com.example.tanorami.base_build_hero.presentation.models

import com.example.base.UiState
import com.example.tanorami.base_build_hero.data.model.FullBaseBuildHero

data class BaseBuildHeroScreenUiState(
    val fullBaseBuildHero: FullBaseBuildHero = FullBaseBuildHero(),
    val visibilityRemarkDialog: Boolean = false,
    val remarkTextInRemarkDialog: String = "",
): UiState
