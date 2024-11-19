package com.example.ui.components.lazy_column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.dp

@androidx.compose.runtime.Composable
fun BaseLazyColumn(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.Companion,
    content: androidx.compose.foundation.lazy.LazyListScope.() -> Unit
) {
    androidx.compose.foundation.lazy.LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        content = content
    )
}