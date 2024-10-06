package com.example.tanorami.info_about_weapon.ui.components

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
internal fun ImagesWeaponAndPath(
    modifier: Modifier = Modifier,
    imageWeapon: String?,
    imagePath: String?,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(30.dp)
                .align(Alignment.TopEnd),
            model = imageWeapon,
            contentDescription = null
        )

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imagePath,
            contentDescription = null,
        )
    }
}