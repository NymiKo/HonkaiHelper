package com.example.domain.repository.hero.model

data class HeroBaseInfoModel(
    val id: Int,
    val name: String,
    val localAvatarPath: String,
    val rarity: Boolean,
)
