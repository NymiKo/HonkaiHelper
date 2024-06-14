package com.example.tanorami.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.tanorami.base_components.BaseHeroAvatarAndName
import com.example.tanorami.base_components.BaseLazyColumn
import com.example.tanorami.core.theme.Blue500
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
            .clip(RoundedCornerShape(16.dp))
            .background(Blue500, RoundedCornerShape(16.dp))
            .clickable {
                onEditTeamScreen(team.idTeam)
            }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BaseHeroAvatarAndName(
                avatarHeroUrl = team.heroOne.localAvatarPath,
                nameHero = team.heroOne.name,
            )

            BaseHeroAvatarAndName(
                avatarHeroUrl = team.heroTwo.localAvatarPath,
                nameHero = team.heroTwo.name,
            )

            BaseHeroAvatarAndName(
                avatarHeroUrl = team.heroThree.localAvatarPath,
                nameHero = team.heroThree.name,
            )

            BaseHeroAvatarAndName(
                avatarHeroUrl = team.heroFour.localAvatarPath,
                nameHero = team.heroFour.name,
            )
        }
    }
}

