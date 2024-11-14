package com.example.core.domain.repository.weapon

import com.example.core.domain.repository.equipment.Equipment

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
