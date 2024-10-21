package com.example.tanorami.base_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.core.theme.DarkGrey
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.core.theme.Violet
import com.example.tanorami.core.theme.White
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity

@Composable
fun BaseHeroAvatarAndName(
    modifier: Modifier = Modifier,
    hero: HeroWithNameAvatarRarity
) {
    Box(
        modifier = modifier
            .height(120.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(topEnd = 16.dp)),
    ) {
        HeroAvatar(avatarHeroUrl = hero.localAvatarPath, rarity = hero.rarity)
        HeroName(modifier = Modifier.align(Alignment.BottomCenter), nameHero = hero.name)
    }
}

@Composable
private fun HeroAvatar(
    modifier: Modifier = Modifier,
    avatarHeroUrl: String,
    rarity: Boolean,
) {
    val backgroundColor = arrayOf(Violet, Orange)

    AsyncImage(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = if (rarity) backgroundColor[1] else backgroundColor[0],
                shape = RoundedCornerShape(topEnd = 16.dp)
            ),
        model = avatarHeroUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun HeroName(
    modifier: Modifier = Modifier,
    nameHero: String,
) {
    Text(
        modifier = modifier
            .height(25.dp)
            .fillMaxWidth()
            .background(DarkGrey),
        text = nameHero,
        textAlign = TextAlign.Center,
        color = White,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Preview
@Composable
private fun HeroAvatarAndNamePreview() {
    BaseHeroAvatarAndName(
        hero = HeroWithNameAvatarRarity(
            id = 1,
            name = "Hero 1",
            localAvatarPath = "",
            rarity = true
        )
    )
}