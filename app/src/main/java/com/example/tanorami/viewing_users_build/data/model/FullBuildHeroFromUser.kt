package com.example.tanorami.viewing_users_build.data.model

import com.example.core.database.models.hero.HeroBaseInfoProjection
import com.example.core.domain.repository.decoration.Decoration
import com.example.core.domain.repository.relic.Relic
import com.example.core.domain.repository.weapon.Weapon

data class FullBuildHeroFromUser(
    val idBuild: Long = -1L,
    val hero: HeroBaseInfoProjection? = null,
    val weapon: Weapon? = null,
    val relicTwoParts: Relic? = null,
    val relicFourParts: Relic? = null,
    val decoration: Decoration? = null,
    val statsEquipment: List<String> = List(4) { "" },
    val nickname: String = "",
    val uid: String = "",
)
