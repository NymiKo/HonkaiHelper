package com.example.core.ui.base_components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ui.base_components.text.BaseDefaultText

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        onClick = onClick::invoke
    ) {
        BaseDefaultText(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}