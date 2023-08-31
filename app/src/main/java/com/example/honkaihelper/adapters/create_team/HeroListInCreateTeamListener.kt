package com.example.honkaihelper.adapters.create_team

import com.example.honkaihelper.models.ActiveHeroInTeam
import com.example.honkaihelper.models.Hero

interface HeroListInCreateTeamListener {
    fun onClick(activeHeroInTeam: ActiveHeroInTeam)
}