package com.example.tanorami.base_build_hero.data.model

import com.example.common.DecorationModel
import com.example.common.RelicModel
import com.example.common.WeaponModel
import com.example.domain.repository.optimal_stats_hero.model.OptimalStatHeroForBuildModel

data class FullBaseBuildHero(
    val idHero: Int = 0,
    val weaponsList: List<WeaponModel> = emptyList(),
    val relicsList: List<RelicModel> = emptyList(),
    val decorationsList: List<DecorationModel> = emptyList(),
    val buildStatsEquipment: List<String> = emptyList(),
    val buildAdditionalStatsList: List<String> = emptyList(),
    val buildOptimalStatsHeroList: List<OptimalStatHeroForBuildModel> = emptyList(),
)