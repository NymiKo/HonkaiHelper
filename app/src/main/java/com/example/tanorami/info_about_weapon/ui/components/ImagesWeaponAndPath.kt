package com.example.tanorami.info_about_weapon.ui.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.info_about_weapon.data.model.FullInfoAboutWeapon
import com.example.tanorami.navigation.LocalNavAnimatedVisibilityScope
import com.example.tanorami.navigation.LocalSharedTransitionScope

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun ImagesWeaponAndPath(
    modifier: Modifier = Modifier,
    weapon: FullInfoAboutWeapon?,
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No AnimatedVisibility found")

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
            model = weapon?.weapon?.path,
            contentDescription = null
        )

        with(sharedTransitionScope) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp)
                    .align(Alignment.Center)
                    .sharedElement(
                        rememberSharedContentState(key = "weapon-${weapon?.weapon?.idWeapon}-base-build"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                model = weapon?.weapon?.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}