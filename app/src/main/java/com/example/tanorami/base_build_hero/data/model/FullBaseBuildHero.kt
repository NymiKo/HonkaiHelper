package com.example.tanorami.base_build_hero.data.model

import com.example.domain.repository.decoration.DecorationModel
import com.example.domain.repository.relic.RelicModel
import com.example.domain.repository.weapon.models.WeaponModel

data class FullBaseBuildHero(
    val id: Int,
    val weapons: List<WeaponModel>,
    val relics: List<RelicModel>,
    val decoration: List<DecorationModel>,
    val buildStatsEquipment: List<String>
)