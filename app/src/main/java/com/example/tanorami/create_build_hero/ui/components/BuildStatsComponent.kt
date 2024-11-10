package com.example.tanorami.create_build_hero.ui.components

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.core.ui.base_components.text.BaseDefaultText
import com.example.tanorami.core.ui.theme.AppTheme
import com.example.tanorami.core.ui.theme.DarkGrey
import com.example.tanorami.core.ui.theme.White
import com.example.tanorami.create_build_hero.data.model.BuildStatsEquipment

@Composable
fun BuildStatsComponent(
    modifier: Modifier = Modifier,
    currentValue: BuildStatsEquipment,
    changeStatOnBody: (value: String) -> Unit,
    changeStatOnLegs: (value: String) -> Unit,
    changeStatOnSphere: (value: String) -> Unit,
    changeStatOnRope: (value: String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ItemStat(
            statIcon = R.drawable.relic_piece_body,
            statsList = stringArrayResource(id = R.array.stats_in_body),
            currentValue = currentValue.statBody,
            changeStatOnEquipment = changeStatOnBody::invoke
        )

        ItemStat(
            statIcon = R.drawable.relic_piece_legs,
            statsList = stringArrayResource(id = R.array.stats_in_legs),
            currentValue = currentValue.statLegs,
            changeStatOnEquipment = changeStatOnLegs::invoke
        )

        ItemStat(
            statIcon = R.drawable.relic_piece_sphere,
            statsList = stringArrayResource(id = R.array.stats_in_sphere),
            currentValue = currentValue.statSphere,
            changeStatOnEquipment = changeStatOnSphere::invoke
        )

        ItemStat(
            statIcon = R.drawable.relic_piece_rope,
            statsList = stringArrayResource(id = R.array.stats_in_rope),
            currentValue = currentValue.statRope,
            changeStatOnEquipment = changeStatOnRope::invoke
        )
    }
}

@Composable
fun ItemStat(
    modifier: Modifier = Modifier,
    statIcon: Int,
    statsList: Array<String>,
    currentValue: String,
    changeStatOnEquipment: (value: String) -> Unit,
) {
    val expanded = remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded.value = !expanded.value },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconStatEquipment(statIcon = statIcon)

        StatsSpinner(
            statsList = statsList,
            expanded = expanded,
            currentValue = currentValue,
            changeStatOnEquipment = changeStatOnEquipment::invoke
        )
    }
}

@Composable
fun IconStatEquipment(
    modifier: Modifier = Modifier,
    statIcon: Int,
) {
    AsyncImage(
        modifier = modifier
            .size(55.dp)
            .clip(CircleShape)
            .background(color = DarkGrey, shape = CircleShape)
            .padding(8.dp),
        model = statIcon,
        contentDescription = null,
        colorFilter = ColorFilter.tint(White)
    )
}

@Composable
fun StatsSpinner(
    modifier: Modifier = Modifier,
    statsList: Array<String>,
    expanded: MutableState<Boolean>,
    currentValue: String,
    changeStatOnEquipment: (value: String) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BaseDefaultText(text = currentValue, fontSize = 18.sp)
        Spacer(modifier = Modifier.weight(1F))
        Icon(
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
        )

        DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
            statsList.forEach { titleItem ->
                DropdownMenuItem(text = {
                    BaseDefaultText(text = titleItem, fontSize = 16.sp)
                }, onClick = {
                    expanded.value = false
                    changeStatOnEquipment(titleItem)
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
        BuildStatsComponent(
            changeStatOnBody = { },
            changeStatOnLegs = {},
            changeStatOnSphere = {},
            changeStatOnRope = {},
            currentValue = BuildStatsEquipment()
        )
    }
}