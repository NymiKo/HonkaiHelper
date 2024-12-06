package com.example.teams_and_builds.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.common.TeamHeroModel
import com.example.ui.components.team.TeamsListLazyColumn

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun TeamsListContent(
    modifier: Modifier = Modifier,
    teamsList: List<TeamHeroModel>,
    refreshingTeamsList: Boolean,
    refreshTeamsList: () -> Unit,
) {
    val refreshState = rememberPullRefreshState(
        refreshing = refreshingTeamsList,
        onRefresh = refreshTeamsList::invoke
    )

    Box(modifier = modifier.pullRefresh(refreshState)) {
        TeamsListLazyColumn(teamsList = teamsList)

        PullRefreshIndicator(
            refreshing = refreshingTeamsList,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}