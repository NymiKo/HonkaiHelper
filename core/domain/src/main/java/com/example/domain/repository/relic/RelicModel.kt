package com.example.domain.repository.relic

import com.example.domain.repository.equipment.Equipment


data class RelicModel(
    val idRelic: Int,
    val title: String,
    val descriptionTwoParts: String,
    val descriptionFourParts: String,
    val image: String
) {
    fun toEquipment() = Equipment(
        id = idRelic,
        image = image
    )
}