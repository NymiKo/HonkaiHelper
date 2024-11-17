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
import com.example.core.local.models.hero.HeroBaseInfoProjection
import com.example.core.ui.theme.Grey

@Composable
fun ItemHeroAvatar(
    modifier: Modifier = Modifier,
    heroBaseInfoProjection: HeroBaseInfoProjection,
) {
    AsyncImage(
        modifier = modifier
            .size(80.dp)
            .clip(CircleShape)
            .border(3.dp, Grey, CircleShape),
        model = heroBaseInfoProjection.localAvatarPath,
        contentDescription = null
    )
}

@Preview
@Composable
private fun ItemHeroAvatarPreview() {
    ItemHeroAvatar(heroBaseInfoProjection = HeroBaseInfoProjection(1, "", "", false))
}