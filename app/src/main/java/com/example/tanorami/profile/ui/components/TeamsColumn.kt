package com.example.tanorami.profile.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.TeamHeroModel
import com.example.ui.components.lazy_column.BaseLazyColumn
import com.example.ui.components.team.TeamItem

@Composable
fun TeamsColumn(
    modifier: Modifier = Modifier,
    teamsList: List<TeamHeroModel>,
    onEditTeamScreen: (idTeam: Long) -> Unit,
) {
    if (teamsList.isEmpty()) {
        EmptyBuildsOrTeams()
    } else {
        BaseLazyColumn(modifier = modifier) {
            items(count = teamsList.size, key = { teamsList[it].idTeam }) {
                TeamItem(
                    team = teamsList[it],
                    onEditTeamScreen = onEditTeamScreen::invoke
                )
            }
        }
    }
}

