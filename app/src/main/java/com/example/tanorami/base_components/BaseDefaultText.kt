package com.example.tanorami.base_components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun BaseDefaultText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    color: Color = MaterialTheme.typography.bodyMedium.color,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight? = null,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
        fontWeight = fontWeight,
    )
}