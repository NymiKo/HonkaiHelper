package com.example.tanorami.profile.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(
    modifier: Modifier = Modifier,
    text: Int
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = text), fontWeight = FontWeight.Bold)
        }
    )
}