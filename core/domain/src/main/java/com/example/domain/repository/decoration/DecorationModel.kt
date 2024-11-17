package com.example.domain.repository.decoration

import com.example.domain.repository.equipment.Equipment


data class DecorationModel(
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
