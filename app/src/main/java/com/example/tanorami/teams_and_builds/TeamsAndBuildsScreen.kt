package com.example.tanorami.teams_and_builds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersScreen
import com.example.tanorami.teams.ui.TeamsListScreen
import kotlinx.coroutines.launch

@Composable
fun TeamsAndBuildsScreen(
    viewModelFactory: ViewModelProvider.Factory,
    navController: NavController,
) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val scope = rememberCoroutineScope()
    val tabs = listOf(stringResource(id = R.string.builds), stringResource(id = R.string.teams))

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TabRow(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            contentColor = MaterialTheme.colorScheme.secondary,
            indicator = { tabPositions ->
                if (pagerState.currentPage < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, title ->
                TabItem(
                    selected = pagerState.currentPage == index,
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
            viewModelFactory = viewModelFactory,
            navController = navController,
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

@Composable
private fun TabsContent(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    pagerState: PagerState,
    navController: NavController,
) {
    HorizontalPager(modifier = modifier, state = pagerState) { index ->
        when (index) {
            0 -> {
                BuildsHeroFromUsersScreen(
                    viewModelFactory = viewModelFactory,
                    navController = navController,
                )
            }

            1 -> {
                TeamsListScreen(viewModelFactory = viewModelFactory, navController = navController)
            }
        }
    }
}