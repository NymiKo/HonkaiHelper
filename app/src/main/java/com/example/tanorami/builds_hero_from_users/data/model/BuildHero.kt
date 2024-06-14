package com.example.tanorami.builds_hero_from_users.data.model

data class BuildHero(
    val idBuild: Long,
    val idHero: Int,
    val idWeapon: Int,
    val idRelicTwoParts: Int,
    val idRelicFourParts: Int,
    val idDecoration: Int,
    val buildUser: BuildUser?
)