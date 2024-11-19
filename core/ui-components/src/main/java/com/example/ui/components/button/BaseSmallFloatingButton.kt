package com.example.ui.components.button

@androidx.compose.runtime.Composable
fun BaseSmallFloatingButton(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.Companion,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    androidx.compose.material3.SmallFloatingActionButton(
        modifier = modifier,
        onClick = onClick::invoke,
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
        contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onSecondary,
    ) {
        androidx.compose.material3.Icon(imageVector = icon, contentDescription = null)
    }
}