package com.example.tanorami.viewing_users_build.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.R
import com.example.core.ui.base_components.text.BaseDefaultText
import com.example.tanorami.create_build_hero.ui.components.IconStatEquipment

@Composable
fun BuildStatsColumn(
    modifier: Modifier = Modifier,
    bodyStat: String,
    legsStat: String,
    sphereStat: String,
    ropeStat: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatItem(statIcon = R.drawable.relic_piece_body, statText = bodyStat)
        StatItem(statIcon = R.drawable.relic_piece_legs, statText = legsStat)
        StatItem(statIcon = R.drawable.relic_piece_sphere, statText = sphereStat)
        StatItem(statIcon = R.drawable.relic_piece_rope, statText = ropeStat)
    }
}

@Composable
fun StatItem(
    modifier: Modifier = Modifier,
    statIcon: Int,
    statText: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconStatEquipment(statIcon = statIcon)

        BaseDefaultText(
            text = statText,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}