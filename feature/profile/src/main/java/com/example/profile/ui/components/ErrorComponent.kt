package com.example.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.strings.R

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    loadingProfile: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            com.example.ui.components.text.BaseDefaultText(
                text = stringResource(id = R.string.error),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            AsyncImage(
                modifier = Modifier.size(150.dp),
                model = R.drawable.pom_pom_shock,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Button(
                onClick = { loadingProfile() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = stringResource(id = R.string.retry),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}