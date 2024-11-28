package com.example.tanorami.base_build_hero.presentation.models

import com.example.base.UiState
import com.example.domain.repository.decoration.DecorationModel
import com.example.domain.repository.relic.RelicModel
import com.example.domain.repository.weapon.models.WeaponModel

data class BaseBuildHeroScreenUiState(
    val idHero: Int = 0,
    val weaponsList: List<WeaponModel> = emptyList(),
    val relicsList: List<RelicModel> = emptyList(),
    val decorationsList: List<DecorationModel> = emptyList(),
    val buildStatsEquipment: List<String> = emptyList()
): UiState
