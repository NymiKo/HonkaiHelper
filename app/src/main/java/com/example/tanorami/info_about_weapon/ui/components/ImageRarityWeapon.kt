package com.example.tanorami.info_about_weapon.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.R

@Composable
internal fun ImageRarityWeapon(
    modifier: Modifier = Modifier,
    rarity: Int?,
) {
    AsyncImage(
        modifier = modifier.height(20.dp),
        model = when(rarity) {
            0 -> R.drawable.icon_3_stars
            1 -> R.drawable.icon_4_stars
            2 -> R.drawable.icon_5_stars
            else -> R.drawable.icon_3_stars
        },
        contentDescription = null
    )
}