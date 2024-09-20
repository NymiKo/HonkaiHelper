package com.example.tanorami.base_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BaseSaveFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SmallFloatingActionButton(
        modifier = modifier,
        onClick = onClick::invoke,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Icon(imageVector = Icons.Default.Save, contentDescription = null)
    }
}