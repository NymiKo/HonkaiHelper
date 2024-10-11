package com.example.tanorami.create_build_hero.presentation.components

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
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.core.theme.DarkGray
import com.example.tanorami.core.theme.White

@Composable
fun AvatarHeroImageAndName(
    modifier: Modifier = Modifier,
    heroImage: String?,
    heroName: String?,
) {
    Column(
        modifier = modifier.width(120.dp)
    ) {
        AsyncImage(
            modifier = Modifier.height(150.dp),
            model = heroImage,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        BaseDefaultText(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkGray)
                .padding(8.dp),
            text = heroName ?: "",
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}