package com.example.tanorami.auth.login.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tanorami.base_components.button.BaseButton

@Composable
fun LoginButton(
    isProgress: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    if (isProgress) {
        CircularProgressIndicator(
            modifier = Modifier.size(45.dp),
            color = MaterialTheme.colorScheme.secondary,
        )
    } else {
        BaseButton(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(45.dp),
            text = text,
            onClick = onClick::invoke
        )
    }
}