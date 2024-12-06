package com.example.data.source.user.mapper

import com.example.common.UserAvatarAndNicknameModel
import com.example.data.remote.api.user.model.UserAvatarAndNicknameDto

fun UserAvatarAndNicknameDto.toUserAvatarAndNicknameModel() = UserAvatarAndNicknameModel(
    avatar, nickname
)