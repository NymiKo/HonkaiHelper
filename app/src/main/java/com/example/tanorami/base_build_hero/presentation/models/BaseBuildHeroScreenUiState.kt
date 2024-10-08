package com.example.tanorami.base_build_hero.presentation.models

import com.example.tanorami.base.UiState
import com.example.tanorami.base_build_hero.data.model.Weapon
import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.info_about_hero.data.model.Relic

data class BaseBuildHeroScreenUiState(
    val idHero: Int = 0,
    val weaponsList: List<Weapon> = emptyList(),
    val relicsList: List<Relic> = emptyList(),
    val decorationsList: List<Decoration> = emptyList(),
    val buildStatsEquipment: List<String> = emptyList()
): UiState
