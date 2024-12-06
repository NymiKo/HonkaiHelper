package com.example.ui.components.hero_build

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.HeroBuildModel

@Composable
fun BuildsListLazyColumn(
    modifier: Modifier = Modifier,
    buildsList: List<HeroBuildModel>,
    onBuildClick: (idBuild: Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(buildsList, key = { it.idBuild }) { buildHero ->
            BuildItem(
                heroBuild = buildHero,
                onClick = { onBuildClick(buildHero.idBuild) }
            )
        }
    }
}