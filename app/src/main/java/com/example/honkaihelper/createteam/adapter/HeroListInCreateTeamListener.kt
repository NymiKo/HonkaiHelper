package com.example.honkaihelper.createteam.adapter

import com.example.honkaihelper.models.ActiveHeroInTeam
import com.example.honkaihelper.models.Hero

interface HeroListInCreateTeamListener {
    fun onClick(activeHeroInTeam: ActiveHeroInTeam)
}