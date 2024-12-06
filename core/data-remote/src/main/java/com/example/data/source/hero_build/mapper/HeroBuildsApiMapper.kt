package com.example.data.source.hero_build.mapper

import com.example.data.remote.api.build.model.HeroBuildFromUserDto
import com.example.data.source.user.mapper.toUserAvatarAndNicknameModel
import com.example.domain.repository.hero_builds.model.HeroBuildFromUserModel

fun HeroBuildFromUserDto.toHeroBuildsFromUserModel() = HeroBuildFromUserModel(
    idBuild,
    idHero,
    idWeapon,
    idRelicTwoParts,
    idRelicFourParts,
    idDecoration,
    buildUser?.toUserAvatarAndNicknameModel()
)