package com.example.tanorami.info_about_hero.data.model

import com.example.tanorami.create_build_hero.data.model.Equipment

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
