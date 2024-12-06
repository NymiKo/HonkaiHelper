package com.example.teams_and_builds.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.common.HeroBuildModel
import com.example.ui.components.hero_build.BuildsListLazyColumn

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun BuildsListContent(
    modifier: Modifier = Modifier,
    buildsList: List<HeroBuildModel>,
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
            refreshing = refreshingBuildsList,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}