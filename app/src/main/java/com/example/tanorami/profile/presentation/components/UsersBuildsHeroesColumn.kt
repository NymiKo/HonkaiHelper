package com.example.tanorami.profile.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_build_hero.data.model.Weapon
import com.example.tanorami.base_components.BaseHeroAvatarAndName
import com.example.tanorami.base_components.lazy_column.BaseLazyColumn
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.core.theme.Blue
import com.example.tanorami.core.theme.DarkGray
import com.example.tanorami.core.theme.GreyTransparent20
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.core.theme.Violet
import com.example.tanorami.core.theme.White
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
                onClick = {
                    onEditBuildHeroScreen(heroesBuildsList[index].idBuild)
                },
            )
        }
    }
}

@Composable
fun BuildItem(
    modifier: Modifier = Modifier,
    buildHero: BuildHeroWithUser,
    clickable: Boolean = true,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = GreyTransparent20,
            )
            .clickable(clickable) {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, DarkGray),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BaseHeroAvatarAndName(
                hero = buildHero.hero,
            )

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HeroWeaponBuild(weapon = buildHero.weapon)

                    RelicsColumnBuild(
                        relicTwoParts = buildHero.relicTwoParts,
                        relicFourParts = buildHero.relicFourParts
                    )

                    HeroDecorationBuild(decoration = buildHero.decoration)
                }

                if (buildHero.buildUser != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        BaseDefaultText(
                            text = stringResource(
                                id = R.string.build_from,
                                buildHero.buildUser.nickname
                            ),
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        AsyncImage(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .size(25.dp)
                                .clip(CircleShape)
                                .background(White, CircleShape),
                            model = buildHero.buildUser.avatar,
                            contentDescription = null
                        )
                    }
                }
            }
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
            .height(104.dp)
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