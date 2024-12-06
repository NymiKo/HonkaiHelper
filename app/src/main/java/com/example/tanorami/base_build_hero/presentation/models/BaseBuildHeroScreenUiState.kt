package com.example.tanorami.base_build_hero.presentation.models

import com.example.base.UiState
import com.example.common.RelicModel
import com.example.common.WeaponModel

data class BaseBuildHeroScreenUiState(
    val idHero: Int = 0,
    val weaponsList: List<WeaponModel> = emptyList(),
    val relicsList: List<RelicModel> = emptyList(),
    val decorationsList: List<com.example.common.DecorationModel> = emptyList(),
    val buildStatsEquipment: List<String> = emptyList(),
): UiState
