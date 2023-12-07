package com.example.tanorami.base_build_hero.data.model

data class Weapon(
    val idWeapon: Int,
    val name: String,
    val story: String,
    val description: String,
    val image: String = "",
    val path: Int,
    val rarity: Int
)
