package com.example.tanorami.builds_hero_from_users.ui.components

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
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.profile.ui.components.BuildItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BuildsListLazyColumn(
    modifier: Modifier = Modifier,
    refreshingBuildsList: Boolean,
    buildsList: List<BuildHeroWithUser>,
    refreshBuildsList: () -> Unit,
    onBuildClick: (idBuild: Long) -> Unit,
) {
    val refreshState = rememberPullRefreshState(
        refreshing = refreshingBuildsList,
        onRefresh = refreshBuildsList::invoke
    )

    Box(modifier = modifier.pullRefresh(refreshState)) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(buildsList, key = { it.idBuild }) { buildHero ->
                BuildItem(
                    buildHero = buildHero,
                    onClick = { onBuildClick(buildHero.idBuild) }
                )
            }
        }

        PullRefreshIndicator(
            refreshingBuildsList,
            refreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}