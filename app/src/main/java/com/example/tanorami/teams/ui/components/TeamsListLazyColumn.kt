package com.example.tanorami.teams.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tanorami.profile.ui.components.TeamItem
import com.example.tanorami.teams.data.model.TeamHero

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeamsListLazyColumn(
    modifier: Modifier = Modifier,
    refreshingTeamsList: Boolean,
    refreshTeamsList: () -> Unit,
    teamsList: List<TeamHero>,
) {
    val refreshState = rememberPullRefreshState(
        refreshing = refreshingTeamsList,
        onRefresh = refreshTeamsList::invoke
    )

    Box(modifier = modifier.pullRefresh(refreshState)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = teamsList,
                key = { team -> team.idTeam }
            ) { team ->
                TeamItem(
                    team = team,
                    clickable = false,
                    onEditTeamScreen = {}
                )
            }
        }

        PullRefreshIndicator(refreshingTeamsList, refreshState, Modifier.align(Alignment.TopCenter))
    }
}