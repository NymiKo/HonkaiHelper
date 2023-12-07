package com.example.tanorami.createteam.adapter

import com.example.tanorami.createteam.data.model.ActiveHeroInTeam

interface HeroListInCreateTeamListener {
    fun onClick(activeHeroInTeam: ActiveHeroInTeam)
}