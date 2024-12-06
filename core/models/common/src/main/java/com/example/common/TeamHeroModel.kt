package com.example.common

data class TeamHeroModel(
    val idTeam: Long,
    val heroOne: HeroBaseInfoModel,
    val heroTwo: HeroBaseInfoModel,
    val heroThree: HeroBaseInfoModel,
    val heroFour: HeroBaseInfoModel,
    val userInfo: UserAvatarAndNicknameModel?,
    val uid: String = "",
)
