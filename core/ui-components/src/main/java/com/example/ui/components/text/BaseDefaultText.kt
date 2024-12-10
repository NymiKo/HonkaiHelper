package com.example.ui.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun BaseDefaultText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 18.sp,
    color: Color = MaterialTheme.colorScheme.secondary,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily = FontFamily.Companion.Default,
    lineHeight: TextUnit = TextUnit.Companion.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Companion.Clip,
    textDecoration: TextDecoration? = null,
) {
    Text(
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