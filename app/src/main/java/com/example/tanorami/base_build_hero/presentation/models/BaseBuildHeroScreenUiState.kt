package com.example.tanorami.base_build_hero.presentation.models

import com.example.core.base.UiState
import com.example.core.domain.repository.decoration.Decoration
import com.example.core.domain.repository.relic.Relic
import com.example.core.domain.repository.weapon.Weapon

data class BaseBuildHeroScreenUiState(
    val idHero: Int = 0,
    val weaponsList: List<Weapon> = emptyList(),
    val relicsList: List<Relic> = emptyList(),
    val decorationsList: List<Decoration> = emptyList(),
    val buildStatsEquipment: List<String> = emptyList()
): UiState
