package com.example.tanorami.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.base_build_hero.data.model.Weapon
import com.example.tanorami.base_components.BaseHeroAvatarAndName
import com.example.tanorami.base_components.BaseLazyColumn
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.core.theme.Blue
import com.example.tanorami.core.theme.DarkGray
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.core.theme.Violet
import com.example.tanorami.info_about_hero.data.model.Decoration

@Composable
fun UsersBuildsHeroesColumn(
    modifier: Modifier = Modifier,
    heroesBuildsList: List<BuildHeroWithUser>,
    onEditBuildHeroScreen: (idBuild: Long) -> Unit,
) {
    BaseLazyColumn(modifier = modifier) {
        items(count = heroesBuildsList.size, key = { heroesBuildsList[it].idBuild }) { index ->
            BuildItem(
                buildHero = heroesBuildsList[index],
                onEditBuildHeroScreen = {
                    onEditBuildHeroScreen(heroesBuildsList[index].idBuild)
                },
            )
        }
    }
}

@Composable
private fun BuildItem(
    modifier: Modifier = Modifier,
    buildHero: BuildHeroWithUser,
    onEditBuildHeroScreen: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, DarkGray, RoundedCornerShape(16.dp))
            .shadow(2.dp, RoundedCornerShape(16.dp))
            .clickable {
                onEditBuildHeroScreen()
            }
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BaseHeroAvatarAndName(
                avatarHeroUrl = buildHero.hero.localAvatarPath,
                nameHero = buildHero.hero.name,
            )

            HeroWeaponBuild(weapon = buildHero.weapon)

            RelicsColumnBuild(
                relicTwoParts = buildHero.relicTwoParts,
                relicFourParts = buildHero.relicFourParts
            )

            HeroDecorationBuild(decoration = buildHero.decoration)
        }
    }
}

@Composable
private fun HeroWeaponBuild(
    modifier: Modifier = Modifier,
    weapon: Weapon,
) {
    val backgroundColor = arrayOf(Blue, Violet, Orange)

    AsyncImage(
        modifier = modifier
            .height(108.dp)
            .width(65.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor[weapon.rarity], RoundedCornerShape(16.dp)),
        model = weapon.image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun HeroDecorationBuild(
    modifier: Modifier = Modifier,
    decoration: Decoration
) {
    AsyncImage(
        modifier = modifier
            .size(65.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Orange, RoundedCornerShape(16.dp)),
        model = decoration.image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Preview
@Composable
private fun UsersBuildsHeroesColumnPreview() {
    UsersBuildsHeroesColumn(heroesBuildsList = emptyList(), onEditBuildHeroScreen = {})
}