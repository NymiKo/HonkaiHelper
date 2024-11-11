package com.example.tanorami.builds_hero_from_users.data.model

import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.info_about_hero.data.model.Relic
import com.example.tanorami.weapons_list.domain.models.Weapon

data class BuildHeroWithUser(
    val idBuild: Long,
    val hero: HeroWithNameAvatarRarity,
    val weapon: Weapon,
    val relicTwoParts: Relic,
    val relicFourParts: Relic,
    val decoration: Decoration,
    val buildUser: BuildUser?
)