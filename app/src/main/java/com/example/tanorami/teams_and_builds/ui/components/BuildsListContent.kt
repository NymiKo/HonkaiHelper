package com.example.tanorami.teams_and_builds.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.builds_hero_from_users.ui.components.BuildsListLazyColumn

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BuildsListContent(
    modifier: Modifier = Modifier,
    buildsList: List<BuildHeroWithUser>,
    refreshingBuildsList: Boolean,
    refreshBuildsList: () -> Unit,
    onBuildClick: (idBuild: Long) -> Unit,
) {
    val refreshState = rememberPullRefreshState(
        refreshing = refreshingBuildsList,
        onRefresh = refreshBuildsList::invoke
    )

    Box(modifier = modifier.pullRefresh(refreshState)) {
        BuildsListLazyColumn(
            buildsList = buildsList,
            onBuildClick = onBuildClick::invoke,
        )

        PullRefreshIndicator(
            refreshingBuildsList,
            refreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}