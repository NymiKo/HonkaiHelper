package com.example.tanorami.teams.data.model

data class TeamHeroes(
    val idTeam: Long,
    val heroOne: com.example.common.HeroBaseInfoModel,
    val heroTwo: com.example.common.HeroBaseInfoModel,
    val heroThree: com.example.common.HeroBaseInfoModel,
    val heroFour: com.example.common.HeroBaseInfoModel,
    val nickname: String? = null,
    val avatar: String? = null,
    val uid: String = "",
)
