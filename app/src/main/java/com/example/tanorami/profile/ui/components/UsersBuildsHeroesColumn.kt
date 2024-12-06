package com.example.tanorami.profile.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.HeroBuildModel
import com.example.ui.components.hero_build.BuildItem

@Composable
fun UsersBuildsHeroesColumn(
    modifier: Modifier = Modifier,
    heroesBuildsList: List<HeroBuildModel>,
    onEditBuildHeroScreen: (idBuild: Long) -> Unit,
) {
    if (heroesBuildsList.isEmpty()) {
        EmptyBuildsOrTeams()
    } else {
        com.example.ui.components.lazy_column.BaseLazyColumn(modifier = modifier) {
            items(count = heroesBuildsList.size, key = { heroesBuildsList[it].idBuild }) { index ->
                BuildItem(
                    heroBuild = heroesBuildsList[index],
                    onClick = {
                        onEditBuildHeroScreen(heroesBuildsList[index].idBuild)
                    },
                )
            }
        }
    }
}