package com.example.tanorami.teams.presentation.models

sealed interface TeamsFromUsersScreenEvents : com.example.base.UiEvent {
    class GetTeamsFromUsers(val idHero: Int) : TeamsFromUsersScreenEvents
    data object RefreshTeamsList : TeamsFromUsersScreenEvents
    data object OnBack : TeamsFromUsersScreenEvents
}