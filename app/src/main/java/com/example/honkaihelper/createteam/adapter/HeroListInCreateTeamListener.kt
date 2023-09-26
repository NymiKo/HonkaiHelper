package com.example.honkaihelper.createteam.adapter

import com.example.honkaihelper.createteam.data.model.ActiveHeroInTeam

interface HeroListInCreateTeamListener {
    fun onClick(activeHeroInTeam: ActiveHeroInTeam)
}