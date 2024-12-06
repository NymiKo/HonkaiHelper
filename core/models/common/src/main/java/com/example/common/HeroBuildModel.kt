package com.example.common

data class HeroBuildModel(
    val idBuild: Long,
    val hero: HeroBaseInfoModel,
    val weapon: WeaponModel,
    val relicTwoParts: RelicModel,
    val relicFourParts: RelicModel,
    val decoration: DecorationModel,
    val buildUser: UserAvatarAndNicknameModel?,
)
