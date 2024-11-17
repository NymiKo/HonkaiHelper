package com.example.tanorami.create_build_hero.data.model

data class BuildHeroModel(
    val idBuild: Long? = null,
    val weapon: com.example.domain.repository.equipment.Equipment? = null,
    val relicTwoParts: com.example.domain.repository.equipment.Equipment? = null,
    val relicFourParts: com.example.domain.repository.equipment.Equipment? = null,
    val decoration: com.example.domain.repository.equipment.Equipment? = null,
    val uid: String = "",
    val statsEquipmentList: BuildStatsEquipment = BuildStatsEquipment()
)
