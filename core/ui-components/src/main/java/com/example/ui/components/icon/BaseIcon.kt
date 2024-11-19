package com.example.ui.components.icon

@androidx.compose.runtime.Composable
fun BaseIcon(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.Companion,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    tint: androidx.compose.ui.graphics.Color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
) {
    androidx.compose.material3.Icon(
        modifier = modifier,
        imageVector = icon,
        contentDescription = null,
        tint = tint
    )
}