package com.example.core.base

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.shimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 0F,
        targetValue = 2000F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
        ), label = ""
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color.LightGray.copy(alpha = 0.8f),
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.8f),
            ),
            start = Offset(12F, 8F),
            end = Offset(translateAnim, translateAnim),
        )
    )
}