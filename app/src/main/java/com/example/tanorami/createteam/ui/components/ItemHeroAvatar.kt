package com.example.tanorami.createteam.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.core.theme.Grey
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity

@Composable
fun ItemHeroAvatar(
    modifier: Modifier = Modifier,
    heroWithNameAvatarRarity: HeroWithNameAvatarRarity,
) {
    AsyncImage(
        modifier = modifier
            .size(80.dp)
            .clip(CircleShape)
            .border(3.dp, Grey, CircleShape),
        model = heroWithNameAvatarRarity.localAvatarPath,
        contentDescription = null
    )
}

@Preview
@Composable
private fun ItemHeroAvatarPreview() {
    ItemHeroAvatar(heroWithNameAvatarRarity = HeroWithNameAvatarRarity(1, "", "", false))
}