package com.example.tanorami.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.info_about_hero.data.model.Relic

@Composable
fun RelicsColumnBuild(
    modifier: Modifier = Modifier,
    relicTwoParts: Relic,
    relicFourParts: Relic,
) {
    Column(
        modifier = modifier,
        verticalArrangement  = Arrangement.spacedBy(4.dp),
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