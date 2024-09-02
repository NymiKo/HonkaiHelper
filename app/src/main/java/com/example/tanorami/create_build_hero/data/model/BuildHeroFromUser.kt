package com.example.tanorami.create_build_hero.data.model

data class BuildHeroFromUser(
    val idHero: Int,
    val idWeapon: Int,
    val idRelicTwoParts: Int,
    val idRelicFourParts: Int,
    val idDecoration: Int,
    val statsEquipment: Array<String>,
    val nickname: String = "",
    val idBuild: Long? = null,
)
