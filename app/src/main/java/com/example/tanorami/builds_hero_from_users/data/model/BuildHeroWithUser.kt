package com.example.tanorami.builds_hero_from_users.data.model

import com.example.tanorami.base_build_hero.data.model.Weapon
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.info_about_hero.data.model.Relic

data class BuildHeroWithUser(
    val idBuild: Long,
    val hero: HeroWithNameAvatarRarity,
    val weapon: Weapon,
    val relicTwoParts: Relic,
    val relicFourParts: Relic,
    val decoration: Decoration,
    val buildUser: BuildUser?
)