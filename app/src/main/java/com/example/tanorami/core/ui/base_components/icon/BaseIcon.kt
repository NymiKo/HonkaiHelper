package com.example.tanorami.core.ui.base_components.icon

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BaseIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    tint: Color = MaterialTheme.colorScheme.secondary,
) {
    Icon(modifier = modifier, imageVector = icon, contentDescription = null, tint = tint)
}