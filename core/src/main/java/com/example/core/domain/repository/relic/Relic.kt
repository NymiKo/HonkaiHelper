package com.example.core.domain.repository.relic

import com.example.core.domain.repository.equipment.Equipment


data class Relic(
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