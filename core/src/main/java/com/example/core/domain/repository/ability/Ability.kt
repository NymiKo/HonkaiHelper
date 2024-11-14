package com.example.core.domain.repository.ability

data class Ability(
    val idAbility: Int,
    val type: String,
    val title: String,
    val description: String,
    val image: String,
    val idHero: Int
)
