package com.example.ui.components.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@androidx.compose.runtime.Composable
fun TabIndicator(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.Companion,
) {
    androidx.compose.foundation.layout.Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                com.example.core.ui.theme.Orange,
                androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
            )
            .zIndex(1F)
    )
}