package com.example.tanorami.teams.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.core.ui.base_components.text.BaseDefaultText
import com.example.tanorami.core.ui.theme.AppTheme

@Composable
fun EmptyListScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BaseDefaultText(
                text = stringResource(id = R.string.empty_here),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            AsyncImage(
                modifier = Modifier.size(150.dp),
                model = R.drawable.pom_pom,
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun EmptyListScreenPreview() {
    AppTheme {
        EmptyListScreen()
    }
}