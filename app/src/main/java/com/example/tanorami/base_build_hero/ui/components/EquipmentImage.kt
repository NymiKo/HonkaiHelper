package com.example.tanorami.base_build_hero.ui.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.tanorami.navigation.LocalNavAnimatedVisibilityScope
import com.example.tanorami.navigation.LocalSharedTransitionScope
import com.example.ui.components.text.TierWeapon

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun EquipmentImage(
    modifier: Modifier = Modifier,
    equipmentImage: Any,
    sharedElementKeyTransition: String,
    colorFilter: ColorFilter? = null,
    tier: Int? = null,
    onClick: () -> Unit = {},
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No AnimatedVisibility found")

    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .clickable { onClick() },
        ) {
            AsyncImage(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = sharedElementKeyTransition),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                model = equipmentImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = colorFilter,
            )
            if (tier != null) {
                TierWeapon(
                    modifier = Modifier.align(Alignment.TopEnd),
                    tier = if (tier == 1) "I" else "V"
                )
            }
        }
    }
}