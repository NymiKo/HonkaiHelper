package com.example.tanorami.teams.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface TeamsFromUsersScreenEvents : UiEvent {
    class GetTeamsFromUsers(val idHero: Int) : TeamsFromUsersScreenEvents
    data object OnBack : TeamsFromUsersScreenEvents
}