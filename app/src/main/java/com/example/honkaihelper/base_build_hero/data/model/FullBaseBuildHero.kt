package com.example.honkaihelper.base_build_hero.data.model

data class FullBaseBuildHero(
    val id: Int,

    val optimalStatsHero: OptimalStatsHero,

    val weapons: List<Weapon>,

    val buildRelic: List<BuildRelic>,

    val buildDecoration: List<BuildDecoration>,

    val buildStatsEquipment: BuildStatsEquipment
)