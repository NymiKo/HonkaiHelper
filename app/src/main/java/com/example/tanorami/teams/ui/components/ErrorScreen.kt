package com.example.tanorami.teams.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.base_components.text.BaseDefaultText
import com.example.core.ui.theme.AppTheme
import com.example.core.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BaseDefaultText(
                text = stringResource(id = R.string.error),
                fontWeight = FontWeight.SemiBold,
            )

            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = stringResource(id = R.string.retry),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    AppTheme {
        ErrorScreen()
    }
}