package com.example.tanorami.teams.data.model

import com.example.core.local.models.hero.HeroBaseInfoProjection

data class TeamHero(
    val idTeam: Long,
    val heroOne: HeroBaseInfoProjection,
    val heroTwo: HeroBaseInfoProjection,
    val heroThree: HeroBaseInfoProjection,
    val heroFour: HeroBaseInfoProjection,
    val nickname: String? = null,
    val avatar: String? = null,
    val uid: String = ""
)
