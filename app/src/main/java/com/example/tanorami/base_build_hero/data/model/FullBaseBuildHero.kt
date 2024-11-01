package com.example.tanorami.base_build_hero.data.model

import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.info_about_hero.data.model.Relic
import com.example.tanorami.weapons_list.data.models.Weapon

data class FullBaseBuildHero(
    val id: Int,
    val weapons: List<Weapon>,
    val relics: List<Relic>,
    val decoration: List<Decoration>,
    val buildStatsEquipment: List<String>
)