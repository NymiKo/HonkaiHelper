package com.example.tanorami.info_about_hero.data.model

import com.example.tanorami.create_build_hero.data.model.Equipment

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