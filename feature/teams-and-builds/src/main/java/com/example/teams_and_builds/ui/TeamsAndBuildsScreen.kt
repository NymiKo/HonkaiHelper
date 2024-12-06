package com.example.teams_and_builds.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.strings.R
import com.example.teams_and_builds.presentation.TeamsAndBuildsViewModel
import com.example.teams_and_builds.presentation.models.TeamsAndBuildsScreenEvents
import com.example.teams_and_builds.presentation.models.TeamsAndBuildsScreenSideEffects
import com.example.teams_and_builds.presentation.models.TeamsAndBuildsScreenUiState
import com.example.teams_and_builds.ui.components.BuildsListContent
import com.example.teams_and_builds.ui.components.TeamsListContent
import com.example.ui.components.tabs.TabIndicator
import kotlinx.coroutines.launch

@Composable
internal fun TeamsAndBuildsScreen(
    viewModel: TeamsAndBuildsViewModel,
    onBuildHeroClick: (idBuild: Long) -> Unit,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(null).value

    TeamsAndBuildsScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        is TeamsAndBuildsScreenSideEffects.OnViewingBuildHeroFromUserScreen -> {
            onBuildHeroClick(sideEffects.idBuild)
            viewModel.clearEffect()
        }

        null -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TeamsAndBuildsScreenContent(
    uiState: TeamsAndBuildsScreenUiState,
    onEvent: (TeamsAndBuildsScreenEvents) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val scope = rememberCoroutineScope()
    val tabs = listOf(stringResource(id = R.string.builds), stringResource(id = R.string.teams))

    LaunchedEffect(pagerState.currentPage) {
        onEvent(TeamsAndBuildsScreenEvents.ChangeActivePage(pagerState.currentPage))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SecondaryTabRow(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            selectedTabIndex = uiState.activePageIndex,
            contentColor = MaterialTheme.colorScheme.secondary,
            divider = {},
            indicator = {
                TabIndicator(
                    modifier = Modifier
                        .tabIndicatorOffset(uiState.activePageIndex)
                        .padding(vertical = 15.dp, horizontal = 30.dp)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                TabItem(
                    selected = uiState.activePageIndex == index,
                    title = title,
                    selectTab = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        TabsContent(
            pagerState = pagerState,
            uiState = uiState,
            onBuildClick = { idBuild ->
                onEvent(
                    TeamsAndBuildsScreenEvents.OnViewingBuildHeroFromUserScreen(idBuild)
                )
            },
            refreshBuildsList = { onEvent(TeamsAndBuildsScreenEvents.RefreshBuildsList) },
            refreshTeamsList = { onEvent(TeamsAndBuildsScreenEvents.RefreshTeamsList) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TabItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: String,
    selectTab: () -> Unit,
) {
    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Tab(
            modifier = modifier.zIndex(2F),
            selected = selected,
            onClick = { selectTab() },
            text = {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = title
                )
            },
            interactionSource = remember { MutableInteractionSource() },
        )
    }
}

@Composable
private fun TabsContent(
    modifier: Modifier = Modifier,
    uiState: TeamsAndBuildsScreenUiState,
    onBuildClick: (idBuild: Long) -> Unit,
    refreshBuildsList: () -> Unit,
    refreshTeamsList: () -> Unit,
    pagerState: PagerState,
) {
    HorizontalPager(modifier = modifier, state = pagerState) { index ->
        when (index) {
            0 -> {
                BuildsListContent(
                    buildsList = uiState.buildsList,
                    refreshingBuildsList = uiState.refreshingBuildsList,
                    refreshBuildsList = refreshBuildsList,
                    onBuildClick = onBuildClick::invoke,
                )
            }

            1 -> {
                TeamsListContent(
                    teamsList = uiState.teamsList,
                    refreshingTeamsList = uiState.refreshingTeamsList,
                    refreshTeamsList = refreshTeamsList::invoke,
                )
            }
        }
    }
}