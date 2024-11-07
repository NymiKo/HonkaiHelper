package com.example.tanorami.base_build_hero.ui.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.tanorami.core.navigation.LocalNavAnimatedVisibilityScope
import com.example.tanorami.core.navigation.LocalSharedTransitionScope

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun EquipmentImage(
    modifier: Modifier = Modifier,
    equipmentImage: Any,
    sharedElementKeyTransition: String,
    colorFilter: ColorFilter? = null,
    onClick: () -> Unit = {},
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No AnimatedVisibility found")

    with(sharedTransitionScope) {
        AsyncImage(
            modifier = modifier
                .clickable { onClick() }
                .sharedElement(
                    rememberSharedContentState(key = sharedElementKeyTransition),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
            model = equipmentImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = colorFilter,
        )
    }
}