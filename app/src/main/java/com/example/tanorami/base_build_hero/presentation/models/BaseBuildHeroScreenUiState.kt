package com.example.tanorami.base_build_hero.presentation.models

import com.example.tanorami.base.UiState
import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.info_about_hero.data.model.Relic
import com.example.tanorami.weapons_list.data.models.Weapon

data class BaseBuildHeroScreenUiState(
    val idHero: Int = 0,
    val weaponsList: List<Weapon> = emptyList(),
    val relicsList: List<Relic> = emptyList(),
    val decorationsList: List<Decoration> = emptyList(),
    val buildStatsEquipment: List<String> = emptyList()
): UiState
