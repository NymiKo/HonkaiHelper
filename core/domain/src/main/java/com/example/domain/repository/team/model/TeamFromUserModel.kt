package com.example.domain.repository.team.model

import com.example.common.UserAvatarAndNicknameModel

data class TeamFromUserModel(
    val idTeam: Long,
    val idHeroOne: Int,
    val idHeroTwo: Int,
    val idHeroThree: Int,
    val idHeroFour: Int,
    val uid: String = "",
    val userInfo: UserAvatarAndNicknameModel?,
)
