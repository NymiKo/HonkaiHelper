package com.example.domain.repository.ability

data class AbilityModel(
    val idAbility: Int,
    val type: String,
    val title: String,
    val description: String,
    val image: String,
    val idHero: Int
)
