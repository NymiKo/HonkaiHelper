package com.example.tanorami.builds_hero_from_users.data.model

import com.example.core.database.models.hero.HeroBaseInfoProjection
import com.example.core.domain.repository.decoration.Decoration
import com.example.core.domain.repository.relic.Relic
import com.example.core.domain.repository.weapon.Weapon

data class BuildHeroWithUser(
    val idBuild: Long,
    val hero: HeroBaseInfoProjection,
    val weapon: Weapon,
    val relicTwoParts: Relic,
    val relicFourParts: Relic,
    val decoration: Decoration,
    val buildUser: BuildUser?
)