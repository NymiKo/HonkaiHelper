package com.example.tanorami.createteam.data.model

import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity

data class ActiveHeroInTeam(val hero: HeroWithNameAvatarRarity, var active: Boolean = false) {

    companion object {
        fun toActiveHero(hero: HeroWithNameAvatarRarity): ActiveHeroInTeam {
            return ActiveHeroInTeam(hero, false)
        }
    }
}
