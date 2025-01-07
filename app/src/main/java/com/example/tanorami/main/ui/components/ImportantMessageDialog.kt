package com.example.tanorami.main.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ui.components.text.BaseDefaultText

@Composable
fun ImportantMessageDialog(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    onOkButtonClick: (visibility: Boolean) -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        title = {
            BaseDefaultText(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        },
        text = { BaseDefaultText(text = text) },
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { onOkButtonClick(false) }) {
                BaseDefaultText(text = "Закрыть")
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
    )
}