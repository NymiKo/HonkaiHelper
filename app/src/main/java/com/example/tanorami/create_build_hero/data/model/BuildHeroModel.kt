package com.example.tanorami.create_build_hero.data.model

import com.example.tanorami.equipment.data.model.Equipment

data class BuildHeroModel(
    val idBuild: Long? = null,
    val weapon: Equipment? = null,
    val relicTwoParts: Equipment? = null,
    val relicFourParts: Equipment? = null,
    val decoration: Equipment? = null,
    val statsEquipmentList: BuildStatsEquipment = BuildStatsEquipment()
)
