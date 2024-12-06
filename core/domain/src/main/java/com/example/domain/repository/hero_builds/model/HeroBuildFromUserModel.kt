package com.example.domain.repository.hero_builds.model

import com.example.common.UserAvatarAndNicknameModel

data class HeroBuildFromUserModel(
    val idBuild: Long,
    val idHero: Int,
    val idWeapon: Int,
    val idRelicTwoParts: Int,
    val idRelicFourParts: Int,
    val idDecoration: Int,
    val userInfo: UserAvatarAndNicknameModel?,
)
