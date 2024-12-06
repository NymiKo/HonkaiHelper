package com.example.tanorami.teams.data.model

import com.example.data.remote.api.user.model.UserAvatarAndNicknameDto

data class TeamHeroResponse(
    val idTeam: Long,
    val idHeroOne: Int,
    val idHeroTwo: Int,
    val idHeroThree: Int,
    val idHeroFour: Int,
    val userInfo: UserAvatarAndNicknameDto,
    val uid: String = "",
)