package com.example.data.source.team.mapper

import com.example.common.UserAvatarAndNicknameModel
import com.example.data.remote.api.team.model.TeamFromUserDto
import com.example.domain.repository.team.model.TeamFromUserModel

fun TeamFromUserDto.toTeamFromUserModel() = TeamFromUserModel(
    idTeam,
    idHeroOne,
    idHeroTwo,
    idHeroThree,
    idHeroFour,
    uid,
    UserAvatarAndNicknameModel(avatar ?: "", nickname ?: "")
)