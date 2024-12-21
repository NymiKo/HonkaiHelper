package com.example.teams_and_builds.presentation.models

import com.example.base.UiEvent

internal sealed interface TeamsAndBuildsScreenEvents : UiEvent {
    class OnViewingBuildHeroFromUserScreen(val idBuild: Long) : TeamsAndBuildsScreenEvents
    class ChangeActivePage(val activePageIndex: Int) : TeamsAndBuildsScreenEvents
    class UidTeamFieldChanged(val newValue: String) : TeamsAndBuildsScreenEvents
    class UidBuildFieldChanged(val newValue: String) : TeamsAndBuildsScreenEvents
    class OnDoneButtonTeamsFilersClick() : TeamsAndBuildsScreenEvents
    class OnDoneButtonBuildsFilersClick() : TeamsAndBuildsScreenEvents
    data object RefreshBuildsList : TeamsAndBuildsScreenEvents
    data object RefreshTeamsList : TeamsAndBuildsScreenEvents
}