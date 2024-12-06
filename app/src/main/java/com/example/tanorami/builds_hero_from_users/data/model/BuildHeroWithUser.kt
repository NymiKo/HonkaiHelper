package com.example.tanorami.builds_hero_from_users.data.model

import com.example.common.DecorationModel
import com.example.common.HeroBaseInfoModel
import com.example.common.RelicModel
import com.example.common.WeaponModel

data class BuildHeroWithUser(
    val idBuild: Long,
    val hero: HeroBaseInfoModel,
    val weapon: WeaponModel,
    val relicTwoParts: RelicModel,
    val relicFourParts: RelicModel,
    val decoration: DecorationModel,
    val buildUser: BuildUser?
)