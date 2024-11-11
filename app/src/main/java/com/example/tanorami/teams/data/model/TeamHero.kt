package com.example.tanorami.teams.data.model

import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity

data class TeamHero(
    val idTeam: Long,
    val heroOne: HeroWithNameAvatarRarity,
    val heroTwo: HeroWithNameAvatarRarity,
    val heroThree: HeroWithNameAvatarRarity,
    val heroFour: HeroWithNameAvatarRarity,
    val nickname: String? = null,
    val avatar: String? = null,
    val uid: String = ""
)
