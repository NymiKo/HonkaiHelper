package com.example.honkaihelper.builds_hero_from_users.data.model

data class BuildHero(
    val idBuild: Int,
    val idWeapon: Int,
    val idRelic: Int,
    val idDecoration: Int,
    val buildUser: BuildUser
)