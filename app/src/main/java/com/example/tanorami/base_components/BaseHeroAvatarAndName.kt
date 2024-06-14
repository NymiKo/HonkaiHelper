package com.example.tanorami.base_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.tanorami.core.theme.Grey500
import com.example.tanorami.core.theme.White

@Composable
fun BaseHeroAvatarAndName(
    modifier: Modifier = Modifier,
    avatarHeroUrl: String,
    nameHero: String
) {
    Box(
        modifier = modifier
            .height(120.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(topEnd = 16.dp)),
    ) {
        HeroAvatar(avatarHeroUrl = avatarHeroUrl)
        HeroName(modifier = Modifier.align(Alignment.BottomCenter), nameHero = nameHero)
    }
}

@Composable
private fun HeroAvatar(
    modifier: Modifier = Modifier,
    avatarHeroUrl: String,
) {
    AsyncImage(
        modifier = modifier,
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
            .fillMaxWidth()
            .background(Grey500)
            .padding(4.dp),
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
        avatarHeroUrl = "http://f0862137.xsph.ru/images/hero_icon/jingliu.webp",
        nameHero = "Цзинлю"
    )
}