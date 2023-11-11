package com.example.honkaihelper.builds_hero_from_users.data.model

import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.info_about_hero.data.model.Decoration
import com.example.honkaihelper.info_about_hero.data.model.Relic

data class FullBuildHeroFromUser(
    val idBuild: Int,
    val hero: HeroWithNameAvatarRarity,
    val weapon: Weapon,
    val relic: Relic,
    val decoration: Decoration,
    val buildUser: BuildUser
)