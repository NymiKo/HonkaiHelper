package com.example.tanorami.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.core.ui.base_components.tabs.TabIndicator
import com.example.core.R
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.teams.data.model.TeamHero
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsAndBuildsInProfile(
    modifier: Modifier = Modifier,
    heroesBuildsList: List<BuildHeroWithUser>,
    teamsList: List<TeamHero>,
    onEditBuildHeroScreen: (idBuild: Long) -> Unit,
    onEditTeamScreen: (idTeam: Long) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val scope = rememberCoroutineScope()
    val tabs = listOf(R.string.my_builds, R.string.my_teams)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SecondaryTabRow(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            contentColor = MaterialTheme.colorScheme.secondary,
            divider = {},
            indicator = {
                TabIndicator(
                    modifier = Modifier
                        .tabIndicatorOffset(pagerState.currentPage)
                        .padding(vertical = 5.dp, horizontal = 15.dp)
                )
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
            heroesBuildsList = heroesBuildsList,
            teamsList = teamsList,
            onEditBuildHeroScreen = onEditBuildHeroScreen::invoke,
            onEditTeamScreen = onEditTeamScreen::invoke
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TabItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: Int,
    selectTab: () -> Unit,
) {
    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Tab(
            modifier = modifier.zIndex(2F),
            selected = selected,
            onClick = { selectTab() }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = stringResource(id = title)
            )
        }
    }
}

@Composable
private fun TabsContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    heroesBuildsList: List<BuildHeroWithUser>,
    teamsList: List<TeamHero>,
    onEditBuildHeroScreen: (idBuild: Long) -> Unit,
    onEditTeamScreen: (idTeam: Long) -> Unit,
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(start = 16.dp, end =  16.dp),
        pageSpacing = 32.dp,
    ) { index ->
        when (index) {
            0 -> {
                UsersBuildsHeroesColumn(
                    heroesBuildsList = heroesBuildsList,
                    onEditBuildHeroScreen = onEditBuildHeroScreen::invoke
                )
            }

            1 -> {
                TeamsColumn(
                    teamsList = teamsList,
                    onEditTeamScreen = onEditTeamScreen::invoke
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun TeamsAndBuildsInProfilePreview() {
//    TeamsAndBuildsInProfile(
//        heroesBuildsList = emptyList(),
//        teamsList = emptyList(),
//        onEditBuildHeroScreen = {},
//        onEditTeamScreen = {})
//}