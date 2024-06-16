package com.example.tanorami.data.local.models.hero

data class HeroWithNameAvatarRarity(
    val id: Int,
    val name: String,
    val localAvatarPath: String,
    val rarity: Boolean,
)
