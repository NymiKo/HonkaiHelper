package com.example.domain.repository.hero.model

import com.example.domain.repository.build_stats_equipment.BuildStatsEquipmentModel
import com.example.domain.repository.decoration.DecorationForBuildModel
import com.example.domain.repository.relic.RelicForBuildModel
import com.example.domain.repository.weapon.WeaponForBuildModel

data class HeroFullBaseBuildModel(
    val idHero: Int,
    val weapons: List<WeaponForBuildModel>,
    val relics: List<RelicForBuildModel>,
    val decorations: List<DecorationForBuildModel>,
    val statsEquipment: BuildStatsEquipmentModel
)
