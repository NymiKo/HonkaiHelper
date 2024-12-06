package com.example.data.remote.api.hero.model

data class HeroDto(
    val id: Int,
    val name: String,
    val story: String,
    val avatar: String,
    val splashArt: String,
    val rarity: Boolean,
    val path: Int,
    val element: Int,
)
