package com.example.tanorami.teams_and_builds.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface TeamsAndBuildsScreenEvents : UiEvent {
    class OnViewingBuildHeroFromUserScreen(val idBuild: Long) : TeamsAndBuildsScreenEvents
    class SearchTextChanged(val newValue: String) : TeamsAndBuildsScreenEvents
    class ChangeActivePage(val activePageIndex: Int) : TeamsAndBuildsScreenEvents
    data object RefreshBuildsList : TeamsAndBuildsScreenEvents
    data object RefreshTeamsList : TeamsAndBuildsScreenEvents
}