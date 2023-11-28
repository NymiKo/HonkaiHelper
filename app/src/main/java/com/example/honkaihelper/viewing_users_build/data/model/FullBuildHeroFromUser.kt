package com.example.honkaihelper.viewing_users_build.data.model

import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.info_about_hero.data.model.Decoration
import com.example.honkaihelper.info_about_hero.data.model.Relic

data class FullBuildHeroFromUser(
    val idBuild: Int,
    val hero: HeroWithNameAvatarRarity,
    val weapon: Weapon,
    val relicTwoParts: Relic,
    val relicFourParts: Relic,
    val decoration: Decoration,
    val statsEquipment: List<String>,
    val nickname: String
)
