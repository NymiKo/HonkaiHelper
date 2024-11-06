package com.example.tanorami.info_about_relic.ui.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.core.navigation.LocalNavAnimatedVisibilityScope
import com.example.tanorami.core.navigation.LocalSharedTransitionScope

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun ImageRelic(
    modifier: Modifier = Modifier,
    idRelic: Int?,
    imageRelic: String?
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No AnimatedVisibility found")

    with(sharedTransitionScope) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(250.dp)
                .sharedElement(
                    rememberSharedContentState(key = "relic-${idRelic}-base-build"),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
            model = imageRelic ?: "",
            contentDescription = null
        )
    }
}