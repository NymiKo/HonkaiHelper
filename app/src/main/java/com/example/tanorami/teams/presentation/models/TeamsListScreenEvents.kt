package com.example.tanorami.teams.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface TeamsListScreenEvents : UiEvent {
    class GetNameHero(val idHero: Int): TeamsListScreenEvents
    class GetTeamsList(val idHero: Int, val uid: String?): TeamsListScreenEvents
    data object OnCreateTeamScreen: TeamsListScreenEvents
    data object OnBack: TeamsListScreenEvents
}