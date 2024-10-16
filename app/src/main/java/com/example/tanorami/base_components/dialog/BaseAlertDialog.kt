package com.example.tanorami.base_components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.tanorami.R
import com.example.tanorami.base_components.text.BaseDefaultText

@Composable
fun BaseSaveAlertDialog(
    modifier: Modifier = Modifier,
    message: Int,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        text = {
            BaseDefaultText(
                text = stringResource(
                    id = message
                ),
                color = MaterialTheme.colorScheme.secondary,
            )
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                BaseDefaultText(
                    text = stringResource(id = R.string.yes),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest::invoke) {
                BaseDefaultText(
                    text = stringResource(id = R.string.cancellation),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )
}