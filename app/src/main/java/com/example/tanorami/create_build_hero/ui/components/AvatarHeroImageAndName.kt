package com.example.tanorami.create_build_hero.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.core.ui.base_components.text.BaseDefaultText
import com.example.tanorami.core.ui.theme.DarkGrey
import com.example.tanorami.core.ui.theme.Orange
import com.example.tanorami.core.ui.theme.Violet
import com.example.tanorami.core.ui.theme.White

@Composable
fun AvatarHeroImageAndName(
    modifier: Modifier = Modifier,
    heroImage: String?,
    heroName: String?,
    heroRarity: Boolean?,
) {
    Column(
        modifier = modifier.width(120.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .height(150.dp)
                .background(
                    when (heroRarity) {
                        false -> Violet
                        true -> Orange
                        else -> Orange
                    }
                ),
            model = heroImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        BaseDefaultText(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkGrey)
                .padding(8.dp),
            text = heroName ?: "",
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}