package com.example.tanorami.base_build_hero.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.core.ui.base_components.text.BaseDefaultText
import com.example.tanorami.core.ui.theme.DarkGrey

@Composable
internal fun StatsListColumn(
    modifier: Modifier = Modifier,
    statsList: List<String>
) {
    HeaderCategory(
        modifier = modifier,
        headerCategory = R.string.stats_equipment
    )

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val iconList = listOf(
            R.drawable.relic_piece_body,
            R.drawable.relic_piece_legs,
            R.drawable.relic_piece_sphere,
            R.drawable.relic_piece_rope
        )

        statsList.forEachIndexed { index, stat ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(55.dp)
                        .clip(CircleShape)
                        .background(DarkGrey, CircleShape)
                        .padding(8.dp),
                    model = iconList[index],
                    contentDescription = null
                )

                BaseDefaultText(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stat,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}