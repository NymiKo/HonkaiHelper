package com.example.tanorami.info_about_hero.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SplashArtHeroImage(
    modifier: Modifier = Modifier,
    splashArtHero: String?,
    elementHero: String?,
    pathHero: String?,
) {
    Box(
        modifier = modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .height(300.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = splashArtHero,
            contentDescription = null,
        )

        AsyncImage(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(30.dp)
                .align(Alignment.TopStart),
            model = elementHero,
            contentDescription = null,
        )

        AsyncImage(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(30.dp)
                .align(Alignment.TopEnd),
            model = pathHero,
            contentDescription = null,
        )
    }
}