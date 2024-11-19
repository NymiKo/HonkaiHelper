package com.example.ui.components.text

import androidx.compose.ui.unit.sp

@androidx.compose.runtime.Composable
fun BaseDefaultText(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.Companion,
    text: String,
    fontSize: androidx.compose.ui.unit.TextUnit = 18.sp,
    color: androidx.compose.ui.graphics.Color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
    textAlign: androidx.compose.ui.text.style.TextAlign? = null,
    fontWeight: androidx.compose.ui.text.font.FontWeight? = null,
    fontFamily: androidx.compose.ui.text.font.FontFamily = androidx.compose.ui.text.font.FontFamily.Companion.Default,
    lineHeight: androidx.compose.ui.unit.TextUnit = androidx.compose.ui.unit.TextUnit.Companion.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: androidx.compose.ui.text.style.TextOverflow = androidx.compose.ui.text.style.TextOverflow.Companion.Clip,
    textDecoration: androidx.compose.ui.text.style.TextDecoration? = null,
) {
    androidx.compose.material3.Text(
        modifier = modifier,
        text = text,
        style = com.example.core.ui.theme.Typography.bodyMedium,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
        fontWeight = fontWeight,
        lineHeight = lineHeight,
        fontFamily = fontFamily,
        maxLines = maxLines,
        overflow = overflow,
        textDecoration = textDecoration,
    )
}