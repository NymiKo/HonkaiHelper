package com.example.teams_and_builds.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.common.TeamHeroModel
import com.example.ui.components.team.TeamItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun TeamsListContent(
    modifier: Modifier = Modifier,
    teamsList: LazyPagingItems<TeamHeroModel>,
    refreshingTeamsList: Boolean,
    refreshTeamsList: () -> Unit,
) {
    val refreshState = rememberPullRefreshState(
        refreshing = refreshingTeamsList,
        onRefresh = refreshTeamsList::invoke
    )

    Box(modifier = modifier.pullRefresh(refreshState)) {
        Column(verticalArrangement = Arrangement.Center) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(
                    count = teamsList.itemCount,
                ) { index ->
                    teamsList[index]?.let {
                        TeamItem(
                            team = it,
                            clickable = false,
                            onEditTeamScreen = {}
                        )
                    }
                }
                if (teamsList.loadState.append == LoadState.Loading) {
                    item {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = refreshingTeamsList,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}