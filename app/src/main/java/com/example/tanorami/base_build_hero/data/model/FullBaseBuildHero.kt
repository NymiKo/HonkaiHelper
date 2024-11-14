package com.example.tanorami.base_build_hero.data.model

import com.example.core.domain.repository.decoration.Decoration
import com.example.core.domain.repository.relic.Relic
import com.example.core.domain.repository.weapon.Weapon

data class FullBaseBuildHero(
    val id: Int,
    val weapons: List<Weapon>,
    val relics: List<Relic>,
    val decoration: List<Decoration>,
    val buildStatsEquipment: List<String>
)