package com.example.tanorami.teams.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tanorami.profile.presentation.components.TeamItem
import com.example.tanorami.teams.data.model.TeamHero

@Composable
fun TeamsListLazyColumn(
    modifier: Modifier = Modifier,
    teamsList: List<TeamHero>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = teamsList,
            key = { team -> team.idTeam }
        ) { team ->
            TeamItem(
                team = team,
                clickable = false,
                onEditTeamScreen = {}
            )
        }
    }
}