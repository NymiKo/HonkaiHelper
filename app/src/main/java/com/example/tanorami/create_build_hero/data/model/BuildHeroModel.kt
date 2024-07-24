package com.example.tanorami.create_build_hero.data.model

import com.example.tanorami.equipment.data.model.Equipment

data class BuildHeroModel(
    val idBuild: Long? = null,
    val weapon: Equipment,
    val relicTwoParts: Equipment,
    val relicFourParts: Equipment,
    val decoration: Equipment,
    val statsEquipmentList: Array<String> = arrayOf("HP %", "HP %", "HP %", "Эффект пробития %")
)
