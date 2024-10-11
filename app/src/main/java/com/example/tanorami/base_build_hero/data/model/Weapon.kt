package com.example.tanorami.base_build_hero.data.model

import com.example.tanorami.equipment.data.model.Equipment

data class Weapon(
    val idWeapon: Int,
    val name: String,
    val story: String,
    val description: String,
    val image: String = "",
    val path: Int,
    val rarity: Int
) {
    fun toEquipment() = Equipment(
        id = idWeapon,
        image = image,
        rarity = rarity
    )
}
