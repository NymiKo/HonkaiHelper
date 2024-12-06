package com.example.data.remote.api.build.model

import com.example.data.remote.api.user.model.UserAvatarAndNicknameDto

data class HeroBuildFromUserDto(
    val idBuild: Long,
    val idHero: Int,
    val idWeapon: Int,
    val idRelicTwoParts: Int,
    val idRelicFourParts: Int,
    val idDecoration: Int,
    val buildUser: UserAvatarAndNicknameDto?,
)
