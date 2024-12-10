package com.example.tanorami.base_build_hero.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.strings.R
import com.example.ui.components.text.BaseDefaultText

@Composable
fun AdditionalStatsColumn(
    modifier: Modifier = Modifier,
    additionalStatsList: List<String>,
) {
    HeaderStats(
        modifier = modifier,
        headerCategory = R.string.additional_stats
    )

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        additionalStatsList.forEach { stat ->
            BaseDefaultText(text = stat, fontWeight = FontWeight.SemiBold)
        }
    }
}