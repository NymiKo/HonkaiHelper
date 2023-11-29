package com.example.honkaihelper.teams.data.model

import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity

data class TeamHero(
    val idTeam: Long,
    val heroOne: HeroWithNameAvatarRarity,
    val heroTwo: HeroWithNameAvatarRarity,
    val heroThree: HeroWithNameAvatarRarity,
    val heroFour: HeroWithNameAvatarRarity,
    val nickname: String?,
    val avatar: String?
)
