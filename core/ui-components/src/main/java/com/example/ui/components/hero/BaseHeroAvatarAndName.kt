package com.example.ui.components.hero

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.example.common.HeroBaseInfoModel
import com.example.core.ui.theme.DarkGrey
import com.example.core.ui.theme.Orange
import com.example.core.ui.theme.Violet
import com.example.core.ui.theme.White

@Composable
fun BaseHeroAvatarAndName(
    modifier: Modifier = Modifier,
    hero: HeroBaseInfoModel,
) {
    Box(
        modifier = modifier
            .height(120.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(topEnd = 16.dp)),
    ) {
        HeroAvatar(avatarHeroUrl = hero.localAvatarPath, rarity = hero.rarity)
        HeroName(
            modifier = Modifier.align(Alignment.BottomCenter),
            nameHero = hero.name
        )
    }
}

@Composable
private fun HeroAvatar(
    modifier: Modifier = Modifier,
    avatarHeroUrl: String,
    rarity: Boolean,
) {
    AsyncImage(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = if (rarity) Orange else Violet,
                shape = RoundedCornerShape(topEnd = 16.dp)
            ),
        model = avatarHeroUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun HeroName(
    modifier: Modifier = Modifier,
    nameHero: String,
) {
    Text(
        modifier = modifier
            .height(30.dp)
            .fillMaxWidth()
            .background(DarkGrey)
            .wrapContentHeight(),
        text = nameHero,
        textAlign = TextAlign.Center,
        color = White,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 11.sp,
        maxLines = 2,
    )
}

@Preview
@Composable
private fun HeroAvatarAndNamePreview() {
    BaseHeroAvatarAndName(
        hero = HeroBaseInfoModel(
            id = 1,
            name = "Hero 1",
            localAvatarPath = "",
            rarity = true
        )
    )
}