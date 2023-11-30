package com.example.honkaihelper.createteam.data.model

import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.model.Hero

data class ActiveHeroInTeam(val hero: HeroWithNameAvatarRarity, var active: Boolean = false) {

    companion object {
        fun toActiveHero(hero: HeroWithNameAvatarRarity): ActiveHeroInTeam {
            return ActiveHeroInTeam(hero, false)
        }
    }
}
