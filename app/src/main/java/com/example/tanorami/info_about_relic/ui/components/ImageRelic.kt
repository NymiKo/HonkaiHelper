package com.example.tanorami.info_about_relic.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
internal fun ImageRelic(
    modifier: Modifier = Modifier,
    imageRelic: String?
) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp),
        model = imageRelic ?: "",
        contentDescription = null
    )
}