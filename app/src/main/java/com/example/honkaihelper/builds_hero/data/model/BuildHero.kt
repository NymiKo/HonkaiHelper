package com.example.honkaihelper.builds_hero.data.model

import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.profile.data.model.User

data class BuildHero(
    val hero: Hero,
    val weapon: Equipment,
    val relic: Equipment,
    val decoration: Equipment,
    val user: User
)