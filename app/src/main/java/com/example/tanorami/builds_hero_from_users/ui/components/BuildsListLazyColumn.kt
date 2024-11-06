package com.example.tanorami.builds_hero_from_users.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.profile.ui.components.BuildItem

@Composable
fun BuildsListLazyColumn(
    modifier: Modifier = Modifier,
    buildsList: List<BuildHeroWithUser>,
    onBuildClick: (idBuild: Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(buildsList) { buildHero ->
            BuildItem(
                buildHero = buildHero,
                onClick = { onBuildClick(buildHero.idBuild) }
            )
        }
    }
}