package com.example.tanorami.builds_hero_from_users.data.model

import com.example.core.local.models.hero.HeroBaseInfoProjection

data class BuildHeroWithUser(
    val idBuild: Long,
    val hero: HeroBaseInfoProjection,
    val weapon: com.example.domain.repository.weapon.Weapon,
    val relicTwoParts: com.example.domain.repository.relic.Relic,
    val relicFourParts: com.example.domain.repository.relic.Relic,
    val decoration: com.example.domain.repository.decoration.Decoration,
    val buildUser: BuildUser?
)