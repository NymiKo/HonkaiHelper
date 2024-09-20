package com.example.tanorami.createteam.presentation

import com.example.tanorami.createteam.data.model.ActiveHeroInTeam

sealed interface CreateTeamScreenEvents {
    data class AddHeroInTeam(val activeHeroInTeam: ActiveHeroInTeam): CreateTeamScreenEvents
    data class RemoveHeroFromTeam(val activeHeroInTeam: ActiveHeroInTeam): CreateTeamScreenEvents
    data class GetTeam(val idTeam: Long): CreateTeamScreenEvents
}