package com.example.tanorami.load_data.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ui.base_components.button.BaseButton
import com.example.core.ui.base_components.text.BaseDefaultText
import com.example.core.R

@Composable
fun ErrorUploadingDataComponent(
    onRetryClick: () -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BaseDefaultText(
            text = stringResource(id = R.string.unexpected_error),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )

        BaseButton(
            text = stringResource(id = R.string.retry),
            onClick = onRetryClick::invoke
        )

        BaseButton(
            text = stringResource(id = R.string.go_back),
            onClick = onBack::invoke
        )
    }
}