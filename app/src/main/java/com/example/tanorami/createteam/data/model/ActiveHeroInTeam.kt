package com.example.tanorami.createteam.data.model

import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity


data class ActiveHeroInTeam(val hero: HeroWithNameAvatarRarity, var active: Boolean = false)
