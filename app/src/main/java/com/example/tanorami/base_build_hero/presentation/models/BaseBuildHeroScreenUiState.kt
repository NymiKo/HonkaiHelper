package com.example.tanorami.base_build_hero.presentation.models

import com.example.core.base.UiState

data class BaseBuildHeroScreenUiState(
    val idHero: Int = 0,
    val weaponsList: List<com.example.domain.repository.weapon.Weapon> = emptyList(),
    val relicsList: List<com.example.domain.repository.relic.Relic> = emptyList(),
    val decorationsList: List<com.example.domain.repository.decoration.Decoration> = emptyList(),
    val buildStatsEquipment: List<String> = emptyList()
): UiState
