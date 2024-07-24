package com.example.tanorami.create_build_hero.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.core.theme.DarkGray
import com.example.tanorami.core.theme.White

@Composable
fun BuildStatsComponent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ItemStat(
            statImage = R.drawable.relic_piece_body, statsList = listOf(
                R.string.hp,
                R.string.defense,
                R.string.crit_damage,
                R.string.attack_power,
                R.string.crit_chance,
                R.string.chance_of_hitting_effects,
                R.string.healing_bonus
            )
        )

        ItemStat(
            statImage = R.drawable.relic_piece_legs, statsList = listOf(
                R.string.hp,
                R.string.defense,
                R.string.attack_power,
                R.string.speed,
            )
        )

        ItemStat(
            statImage = R.drawable.relic_piece_sphere, statsList = listOf(
                R.string.hp,
                R.string.defense,
                R.string.attack_power,
                R.string.bonus_physical_damage,
                R.string.bonus_fire_damage,
                R.string.bonus_electric_damage,
                R.string.bonus_quantum_damage,
                R.string.bonus_ice_damage,
                R.string.bonus_wind_damage,
                R.string.bonus_imaginary_damage,
            )
        )

        ItemStat(
            statImage = R.drawable.relic_piece_rope, statsList = listOf(
                R.string.penetration_effect,
                R.string.hp,
                R.string.defense,
                R.string.recovery_energy,
                R.string.attack_power,
            )
        )
    }
}

@Composable
fun ItemStat(
    modifier: Modifier = Modifier,
    statImage: Int,
    statsList: List<Int>,
) {
    val expanded = remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxWidth().clickable { expanded.value = !expanded.value },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .background(color = DarkGray, shape = CircleShape)
                .padding(8.dp),
            model = statImage,
            contentDescription = null,
            colorFilter = ColorFilter.tint(White)
        )

        StatsSpinner(statsList = statsList, expanded = expanded)
    }
}

@Composable
fun StatsSpinner(
    modifier: Modifier = Modifier,
    statsList: List<Int>,
    expanded: MutableState<Boolean>,
) {
    var currentValue by remember { mutableIntStateOf(statsList[0]) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BaseDefaultText(text = stringResource(id = currentValue), fontSize = 18.sp)
        Spacer(modifier = Modifier.weight(1F))
        Icon(
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
        )

        DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
            statsList.forEach { titleItem ->
                DropdownMenuItem(text = {
                    BaseDefaultText(text = stringResource(id = titleItem), fontSize = 16.sp)
                }, onClick = {
                    currentValue = titleItem
                    expanded.value = false
                })
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BuildStatsComponentPreview(modifier: Modifier = Modifier) {
    AppTheme {
        BuildStatsComponent()
    }
}