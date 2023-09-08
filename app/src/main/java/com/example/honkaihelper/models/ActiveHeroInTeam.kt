package com.example.honkaihelper.models

data class ActiveHeroInTeam(val hero: Hero, var active: Boolean) {

    companion object {
        fun toActiveHero(hero: Hero): ActiveHeroInTeam {
            return ActiveHeroInTeam(hero, false)
        }
    }
}
