package com.example.tanorami.base_build_hero.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.Orange
import com.example.domain.repository.optimal_stats_hero.model.OptimalStatHeroForBuildModel
import com.example.strings.R
import com.example.ui.components.text.BaseDefaultText

@Composable
fun OptimalStatsHeroColumn(
    modifier: Modifier = Modifier,
    optimalStatsHeroList: List<OptimalStatHeroForBuildModel>,
    visibilityRemarkDialog: Boolean,
    remark: String,
    changeVisibilityRemarkDialog: (visibility: Boolean, remark: String) -> Unit,
) {
    HeaderStats(
        modifier = modifier,
        headerCategory = R.string.optimal_stats
    )

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        optimalStatsHeroList.forEach { optimalStat ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BaseDefaultText(
                    text = "${optimalStat.statName}:",
                    fontWeight = FontWeight.SemiBold,
                    color = Orange,
                )
                BaseDefaultText(
                    text = optimalStat.statValue,
                    fontWeight = FontWeight.Bold,
                )

                if (!optimalStat.remark.isNullOrEmpty()) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {
                                changeVisibilityRemarkDialog(
                                    true,
                                    optimalStat.remark ?: ""
                                )
                            },
                        imageVector = Icons.Rounded.Info,
                        contentDescription = null,
                        tint = Orange
                    )
                }
            }
        }
    }

    if (visibilityRemarkDialog) {
        RemarkDialog(
            remark = remark,
            onCloseButtonClick = { changeVisibilityRemarkDialog(false, "") }
        )
    }
}

@Composable
private fun RemarkDialog(
    modifier: Modifier = Modifier,
    remark: String,
    onCloseButtonClick: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        text = { BaseDefaultText(text = remark) },
        onDismissRequest = onCloseButtonClick::invoke,
        confirmButton = {
            TextButton(onClick = onCloseButtonClick::invoke) {
                BaseDefaultText(text = stringResource(id = R.string.close))
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
    )
}