package com.example.tanorami.base_build_hero.data.model

data class FullBaseBuildHero(
    val id: Int,
    val weapons: List<com.example.domain.repository.weapon.Weapon>,
    val relics: List<com.example.domain.repository.relic.Relic>,
    val decoration: List<com.example.domain.repository.decoration.Decoration>,
    val buildStatsEquipment: List<String>
)