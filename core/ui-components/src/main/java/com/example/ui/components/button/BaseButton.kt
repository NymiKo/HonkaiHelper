package com.example.ui.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.text.BaseDefaultText

@androidx.compose.runtime.Composable
fun BaseButton(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.Companion,
    fontSize: androidx.compose.ui.unit.TextUnit = 18.sp,
    text: String,
    onClick: () -> Unit,
) {
    androidx.compose.material3.Button(
        modifier = modifier,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary
        ),
        onClick = onClick::invoke
    ) {
        BaseDefaultText(
            modifier = androidx.compose.ui.Modifier.Companion.fillMaxWidth(),
            text = text,
            textAlign = androidx.compose.ui.text.style.TextAlign.Companion.Center,
            fontSize = fontSize,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Companion.Bold,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSecondary,
        )
    }
}