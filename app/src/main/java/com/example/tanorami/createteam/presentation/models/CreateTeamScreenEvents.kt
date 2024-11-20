package com.example.tanorami.createteam.presentation.models

import com.example.base.UiEvent
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam

sealed interface CreateTeamScreenEvents : UiEvent {
    data class AddHeroInTeam(val activeHeroInTeam: ActiveHeroInTeam) : CreateTeamScreenEvents
    data class RemoveHeroFromTeam(val activeHeroInTeam: ActiveHeroInTeam) : CreateTeamScreenEvents
    data class GetTeam(val idTeam: Long?) : CreateTeamScreenEvents
    class ChangeVisibilityDialogDeleteTeam(val visibility: Boolean) : CreateTeamScreenEvents
    data object DeleteTeam : CreateTeamScreenEvents
    data object SaveTeam : CreateTeamScreenEvents
    data object OnBack : CreateTeamScreenEvents
}