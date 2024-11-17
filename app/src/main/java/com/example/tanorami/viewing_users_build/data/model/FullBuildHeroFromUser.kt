package com.example.tanorami.viewing_users_build.data.model

import com.example.core.local.models.hero.HeroBaseInfoProjection

data class FullBuildHeroFromUser(
    val idBuild: Long = -1L,
    val hero: HeroBaseInfoProjection? = null,
    val weapon: com.example.domain.repository.weapon.Weapon? = null,
    val relicTwoParts: com.example.domain.repository.relic.Relic? = null,
    val relicFourParts: com.example.domain.repository.relic.Relic? = null,
    val decoration: com.example.domain.repository.decoration.Decoration? = null,
    val statsEquipment: List<String> = List(4) { "" },
    val nickname: String = "",
    val uid: String = "",
)
