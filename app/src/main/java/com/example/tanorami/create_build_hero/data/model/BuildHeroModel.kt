package com.example.tanorami.create_build_hero.data.model

data class BuildHeroModel(
    val idBuild: Long? = null,
    val weapon: Equipment? = null,
    val relicTwoParts: Equipment? = null,
    val relicFourParts: Equipment? = null,
    val decoration: Equipment? = null,
    val uid: String = "",
    val statsEquipmentList: BuildStatsEquipment = BuildStatsEquipment()
)
