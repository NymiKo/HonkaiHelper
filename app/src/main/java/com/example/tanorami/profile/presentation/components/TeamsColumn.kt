package com.example.tanorami.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.tanorami.base_components.BaseHeroAvatarAndName
import com.example.tanorami.base_components.BaseLazyColumn
import com.example.tanorami.core.theme.Blue500
import com.example.tanorami.core.theme.GreyTransparent20
import com.example.tanorami.teams.data.model.TeamHero

@Composable
fun TeamsColumn(
    modifier: Modifier = Modifier,
    teamsList: List<TeamHero>,
    onEditTeamScreen: (idTeam: Long) -> Unit,
) {
    BaseLazyColumn(modifier = modifier) {
        items(count = teamsList.size, key = { teamsList[it].idTeam }) {
            TeamItem(
                team = teamsList[it],
                onEditTeamScreen = onEditTeamScreen::invoke
            )
        }
    }
}

@Composable
private fun TeamItem(
    modifier: Modifier = Modifier,
    team: TeamHero,
    onEditTeamScreen: (idTeam: Long) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = GreyTransparent20,
            )
            .clickable {
                onEditTeamScreen(team.idTeam)
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Blue500),
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BaseHeroAvatarAndName(
                hero = team.heroOne,
            )

            BaseHeroAvatarAndName(
                hero = team.heroTwo,
            )

            BaseHeroAvatarAndName(
                hero = team.heroThree,
            )

            BaseHeroAvatarAndName(
                hero = team.heroFour,
            )
        }
    }
}

