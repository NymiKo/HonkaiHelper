package com.example.tanorami.base_components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit

@Composable
fun BaseDefaultText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontSize = fontSize,
    )
}