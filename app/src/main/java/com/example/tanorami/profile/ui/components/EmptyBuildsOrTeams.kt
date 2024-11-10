package com.example.tanorami.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.core.ui.base_components.text.BaseDefaultText

@Composable
fun EmptyBuildsOrTeams(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BaseDefaultText(
                text = stringResource(id = R.string.empty),
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                modifier = Modifier.size(150.dp),
                model = R.drawable.pom_pom_empty,
                contentDescription = null,
            )
        }
    }
}