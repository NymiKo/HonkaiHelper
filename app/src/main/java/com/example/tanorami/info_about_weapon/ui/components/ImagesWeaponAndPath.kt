package com.example.tanorami.info_about_weapon.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import com.example.tanorami.info_about_weapon.data.model.FullInfoAboutWeapon

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun ImagesWeaponAndPath(
    modifier: Modifier = Modifier,
    weapon: FullInfoAboutWeapon?,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
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
            model = weapon?.weapon?.path,
            contentDescription = null
        )

        with(sharedTransitionScope) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .sharedElement(
                        rememberSharedContentState(key = "weapon-${weapon?.weapon?.idWeapon}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                model = weapon?.weapon?.image,
                contentDescription = null,
            )
        }
    }
}