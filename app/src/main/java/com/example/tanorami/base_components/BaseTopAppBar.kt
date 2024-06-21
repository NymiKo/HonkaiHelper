package com.example.tanorami.base_components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: Boolean = true,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
        },
        actions = actions,
        navigationIcon = {
            if (navigationIcon) {
                Icon(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    )
}