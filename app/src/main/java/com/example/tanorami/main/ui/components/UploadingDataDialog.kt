package com.example.tanorami.main.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.tanorami.R
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.core.theme.Orange

@Composable
fun UploadingDataDialog(
    modifier: Modifier = Modifier,
    text: String,
    onOkButtonClick: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        title = {
            BaseDefaultText(
                text = stringResource(id = R.string.data_needs_updated),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        },
        text = { BaseDefaultText(text = text) },
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = onOkButtonClick::invoke) {
                BaseDefaultText(text = stringResource(id = R.string.ok))
            }
        },
        icon = {
            Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Orange)
        },
        containerColor = MaterialTheme.colorScheme.background,
    )
}