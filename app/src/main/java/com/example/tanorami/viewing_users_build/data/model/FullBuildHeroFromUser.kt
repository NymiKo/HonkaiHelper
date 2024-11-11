package com.example.tanorami.viewing_users_build.data.model

import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.info_about_hero.data.model.Relic
import com.example.tanorami.weapons_list.domain.models.Weapon

data class FullBuildHeroFromUser(
    val idBuild: Long = -1L,
    val hero: HeroWithNameAvatarRarity? = null,
    val weapon: Weapon? = null,
    val relicTwoParts: Relic? = null,
    val relicFourParts: Relic? = null,
    val decoration: Decoration? = null,
    val statsEquipment: List<String> = List(4) { "" },
    val nickname: String = "",
    val uid: String = "",
)
