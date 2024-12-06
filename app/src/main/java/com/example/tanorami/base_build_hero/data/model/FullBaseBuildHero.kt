package com.example.tanorami.base_build_hero.data.model

import com.example.common.WeaponModel

data class FullBaseBuildHero(
    val id: Int,
    val weapons: List<WeaponModel>,
    val relics: List<com.example.common.RelicModel>,
    val decoration: List<com.example.common.DecorationModel>,
    val buildStatsEquipment: List<String>,
)