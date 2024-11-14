package com.example.tanorami.createteam.data.model

import com.example.core.database.models.hero.HeroBaseInfoProjection

data class ActiveHeroInTeam(val hero: HeroBaseInfoProjection, var active: Boolean = false)
