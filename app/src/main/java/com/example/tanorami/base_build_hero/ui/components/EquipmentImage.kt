package com.example.tanorami.base_build_hero.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
internal fun EquipmentImage(
    modifier: Modifier = Modifier,
    equipmentImage: Any,
    colorFilter: ColorFilter? = null,
    onClick: () -> Unit = {},
) {
    AsyncImage(
        modifier = modifier.clickable { onClick() },
        model = equipmentImage,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        colorFilter = colorFilter,
    )
}