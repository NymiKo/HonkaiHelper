package com.example.tanorami.core.database.models.hero

data class HeroWithNameAvatarRarity(
    val id: Int,
    val name: String,
    val localAvatarPath: String,
    val rarity: Boolean,
)
