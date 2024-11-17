package com.example.domain.repository.weapon

import com.example.domain.repository.equipment.Equipment

data class WeaponModel(
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
