package com.example.tanorami.teams_and_builds.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.builds_hero_from_users.ui.components.BuildsListLazyColumn
import com.example.tanorami.teams.data.model.TeamHero
import com.example.tanorami.teams.ui.components.TeamsListLazyColumn
import com.example.tanorami.teams_and_builds.presentation.TeamsAndBuildsViewModel
import com.example.tanorami.teams_and_builds.presentation.models.TeamsAndBuildsScreenEvents
import com.example.tanorami.teams_and_builds.presentation.models.TeamsAndBuildsScreenSideEffects
import com.example.tanorami.teams_and_builds.presentation.models.TeamsAndBuildsScreenUiState
import com.example.tanorami.viewing_users_build.ui.ViewingBuildHeroFromUserNavArguments
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TeamsAndBuildsScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: TeamsAndBuildsViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value

    TeamsAndBuildsScreenContent(
        uiState = state,
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        is TeamsAndBuildsScreenSideEffects.OnViewingBuildHeroFromUserScreen -> {
            navController.navigate(route = ViewingBuildHeroFromUserNavArguments(idBuild = sideEffects.idBuild))
            viewModel.clearEffect()
        }

        null -> {}
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun TeamsAndBuildsScreenContent(
    uiState: TeamsAndBuildsScreenUiState,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onEvent: (TeamsAndBuildsScreenEvents) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val scope = rememberCoroutineScope()
    val tabs = listOf(stringResource(id = R.string.builds), stringResource(id = R.string.teams))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TabRow(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            selectedTabIndex = uiState.activePageIndex,
            contentColor = MaterialTheme.colorScheme.secondary,
            indicator = { tabPositions ->
                if (uiState.activePageIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[uiState.activePageIndex]),
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
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
                        onEvent(TeamsAndBuildsScreenEvents.ChangeActivePage(index))
                    }
                )
            }
        }

        TabsContent(
            pagerState = pagerState,
            buildsList = uiState.buildsList,
            teamsList = uiState.teamsList,
            onBuildClick = { idBuild ->
                onEvent(
                    TeamsAndBuildsScreenEvents.OnViewingBuildHeroFromUserScreen(idBuild)
                )
            },
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
}

@Composable
private fun TabItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: String,
    selectTab: () -> Unit,
) {
    Tab(
        modifier = modifier,
        selected = selected,
        onClick = { selectTab() }
    ) {
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text = title
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun TabsContent(
    modifier: Modifier = Modifier,
    teamsList: List<TeamHero>,
    buildsList: List<BuildHeroWithUser>,
    onBuildClick: (idBuild: Long) -> Unit,
    pagerState: PagerState,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    HorizontalPager(modifier = modifier, state = pagerState) { index ->
        when (index) {
            0 -> {
                BuildsListLazyColumn(
                    buildsList = buildsList,
                    onBuildClick = onBuildClick::invoke,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                )
            }

            1 -> {
                TeamsListLazyColumn(teamsList = teamsList)
            }
        }
    }
}