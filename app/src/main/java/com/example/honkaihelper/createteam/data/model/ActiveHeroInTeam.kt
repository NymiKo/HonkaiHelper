package com.example.honkaihelper.createteam.data.model

import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.model.Hero

data class ActiveHeroInTeam(val hero: Hero, var active: Boolean) {

    companion object {
        fun toActiveHero(hero: Hero): ActiveHeroInTeam {
            return ActiveHeroInTeam(hero, false)
        }
    }
}
