package com.example.tanorami.builds_hero_from_users.data.model

import com.example.domain.repository.decoration.DecorationModel
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.domain.repository.relic.RelicModel
import com.example.domain.repository.weapon.WeaponModel


data class BuildHeroWithUser(
    val idBuild: Long,
    val hero: HeroBaseInfoModel,
    val weapon: WeaponModel,
    val relicTwoParts: RelicModel,
    val relicFourParts: RelicModel,
    val decoration: DecorationModel,
    val buildUser: BuildUser?
)