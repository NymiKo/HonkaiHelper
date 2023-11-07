package com.example.honkaihelper.base_build_hero.data.model

import com.example.honkaihelper.info_about_hero.data.model.Decoration
import com.example.honkaihelper.info_about_hero.data.model.Relic

data class FullBaseBuildHero(
    val id: Int,

    val optimalStatsHero: OptimalStatsHero,

    val weapons: List<Weapon>,

    val relics: List<Relic>,

    val decoration: List<Decoration>,

    val buildStatsEquipment: BuildStatsEquipment
)