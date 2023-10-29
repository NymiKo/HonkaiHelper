package com.example.honkaihelper.info_about_hero.data.model

data class Ability(
    val idAbility: Int,
    val type: String,
    val title: String,
    val description: String,
    val image: String,
    val idHero: Int
)
