package com.example.tanorami.base_components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.tanorami.core.theme.Typography

@Composable
fun BaseDefaultText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 18.sp,
    color: Color = MaterialTheme.colorScheme.secondary,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        modifier = modifier,
        text = text,
        style = Typography.bodyMedium,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
        fontWeight = fontWeight,
        lineHeight = lineHeight,
        maxLines = maxLines
    )
}