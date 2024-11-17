package com.example.data.local.models.hero

data class HeroBaseInfoProjection(
    val id: Int,
    val name: String,
    val localAvatarPath: String,
    val rarity: Boolean,
)
