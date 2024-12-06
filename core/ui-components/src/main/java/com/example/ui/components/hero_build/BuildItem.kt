package com.example.ui.components.hero_build

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
import com.example.common.DecorationModel
import com.example.common.HeroBuildModel
import com.example.common.RelicModel
import com.example.common.WeaponModel
import com.example.core.ui.theme.Blue
import com.example.core.ui.theme.DarkGrey
import com.example.core.ui.theme.GreyTransparent20
import com.example.core.ui.theme.Orange
import com.example.core.ui.theme.Violet
import com.example.core.ui.theme.White
import com.example.strings.R
import com.example.ui.components.hero.BaseHeroAvatarAndName

@Composable
fun BuildItem(
    modifier: Modifier = Modifier,
    heroBuild: HeroBuildModel,
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
        border = BorderStroke(1.dp, DarkGrey),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BaseHeroAvatarAndName(
                hero = heroBuild.hero,
            )

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HeroWeaponBuild(weapon = heroBuild.weapon)

                    RelicsColumnBuild(
                        relicTwoParts = heroBuild.relicTwoParts,
                        relicFourParts = heroBuild.relicFourParts
                    )

                    HeroDecorationBuild(decoration = heroBuild.decoration)
                }

                if (heroBuild.buildUser != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        com.example.ui.components.text.BaseDefaultText(
                            text = stringResource(
                                id = R.string.build_from,
                                heroBuild.buildUser?.nickname ?: ""
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
                            model = heroBuild.buildUser?.avatar,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
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
    weapon: WeaponModel,
) {
    val backgroundColor = arrayOf(Blue, Violet, Orange)

    AsyncImage(
        modifier = modifier
            .height(104.dp)
            .width(65.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                backgroundColor[weapon.rarity],
                RoundedCornerShape(16.dp)
            ),
        model = weapon.image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun HeroDecorationBuild(
    modifier: Modifier = Modifier,
    decoration: DecorationModel,
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

@Composable
fun RelicsColumnBuild(
    modifier: Modifier = Modifier,
    relicTwoParts: RelicModel,
    relicFourParts: RelicModel,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        RelicImage(
            relicImage = relicTwoParts.image
        )
        RelicImage(
            relicImage = relicFourParts.image
        )
    }
}

@Composable
private fun RelicImage(
    modifier: Modifier = Modifier,
    relicImage: String,
) {
    AsyncImage(
        modifier = modifier
            .size(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Orange, RoundedCornerShape(16.dp)),
        model = relicImage,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}