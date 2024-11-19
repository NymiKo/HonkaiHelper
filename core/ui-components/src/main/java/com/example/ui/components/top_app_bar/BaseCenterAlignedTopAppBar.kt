package com.example.ui.components.top_app_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun BaseCenterAlignedTopAppBar(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.Companion,
    title: String?,
    onBack: () -> Unit,
) {
    androidx.compose.material3.CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            androidx.compose.material3.Text(
                text = title ?: "",
                fontWeight = androidx.compose.ui.text.font.FontWeight.Companion.Bold,
                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
                maxLines = 1,
                fontSize = 20.sp,
                overflow = androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
            )
        },
        navigationIcon = {
            androidx.compose.material3.Icon(
                modifier = androidx.compose.ui.Modifier.Companion
                    .clip(androidx.compose.foundation.shape.CircleShape)
                    .clickable { onBack() }
                    .padding(8.dp),
                imageVector = androidx.compose.material.icons.Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                tint = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            )
        }
    )
}