package com.example.core.domain.repository.decoration

import com.example.core.domain.repository.equipment.Equipment


data class Decoration(
    val idDecoration: Int,
    val title: String,
    val description: String,
    val image: String
) {
    fun toEquipment() = Equipment(
        id = idDecoration,
        image = image
    )
}
