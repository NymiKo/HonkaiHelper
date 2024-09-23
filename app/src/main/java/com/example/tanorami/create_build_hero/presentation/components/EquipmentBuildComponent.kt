package com.example.tanorami.create_build_hero.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.core.theme.Blue
import com.example.tanorami.core.theme.GreyTransparent20
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.core.theme.Violet
import com.example.tanorami.equipment.EquipmentType
import com.example.tanorami.equipment.data.model.Equipment

@Composable
fun EquipmentBuildComponent(
    modifier: Modifier = Modifier,
    weapon: Equipment?,
    relicTwoParts: Equipment?,
    relicFourParts: Equipment?,
    decoration: Equipment?,
    onEquipmentScreen: (equipmentType: EquipmentType) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CategoryWeapon(equipment = weapon, onEquipmentScreen = onEquipmentScreen::invoke)
        CategoryRelics(
            relicTwoPartsEquipment = relicTwoParts,
            relicFourPartsEquipment = relicFourParts,
            onEquipmentScreen = onEquipmentScreen::invoke
        )
        CategoryDecoration(
            equipment = decoration, onEquipmentScreen = onEquipmentScreen::invoke
        )
    }
}

@Composable
fun CategoryWeapon(
    modifier: Modifier = Modifier,
    equipment: Equipment?,
    onEquipmentScreen: (equipmentType: EquipmentType) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EquipmentCategoryText(categoryText = stringResource(id = R.string.weapon))
        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
            EquipmentImage(
                modifier = Modifier
                    .height(120.dp)
                    .width(80.dp)
                    .background(
                        color = when (equipment?.rarity) {
                            0 -> Blue
                            1 -> Violet
                            2 -> Orange
                            else -> Color.Transparent
                        },
                        shape = RoundedCornerShape(16.dp)
                    ),
                equipment = equipment,
                contentScale = ContentScale.Crop,
                onEquipmentScreen = { onEquipmentScreen(EquipmentType.WEAPON) }
            )
        }
    }
}

@Composable
fun CategoryRelics(
    modifier: Modifier = Modifier,
    relicTwoPartsEquipment: Equipment?,
    relicFourPartsEquipment: Equipment?,
    onEquipmentScreen: (equipmentType: EquipmentType) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EquipmentCategoryText(categoryText = stringResource(id = R.string.relic))
        RelicImage(equipment = relicTwoPartsEquipment,
            textRelicParts = stringResource(id = R.string.relic_two_parts),
            onEquipmentScreen = { onEquipmentScreen(EquipmentType.RELIC_TWO_PARTS) })
        RelicImage(equipment = relicFourPartsEquipment,
            textRelicParts = stringResource(id = R.string.relic_four_parts),
            onEquipmentScreen = { onEquipmentScreen(EquipmentType.RELIC_FOUR_PARTS) })
    }
}

@Composable
fun CategoryDecoration(
    modifier: Modifier = Modifier,
    equipment: Equipment?,
    onEquipmentScreen: (equipmentType: EquipmentType) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EquipmentCategoryText(categoryText = stringResource(id = R.string.decoration))
        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
            EquipmentImage(modifier = Modifier
                .size(100.dp)
                .background(
                    color = if (equipment == null) Color.Transparent else Orange,
                    shape = RoundedCornerShape(16.dp)
                ),
                equipment = equipment,
                onEquipmentScreen = { onEquipmentScreen(EquipmentType.DECORATION) })
        }
    }
}

@Composable
fun EquipmentCategoryText(
    modifier: Modifier = Modifier,
    categoryText: String,
) {
    BaseDefaultText(
        modifier = modifier.padding(top = 16.dp),
        text = categoryText,
        fontSize = 18.sp,
    )
}

@Composable
fun RelicImage(
    modifier: Modifier = Modifier,
    equipment: Equipment?,
    textRelicParts: String,
    onEquipmentScreen: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EquipmentImage(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = if (equipment == null) Color.Transparent else Orange,
                    shape = RoundedCornerShape(16.dp)
                ),
            equipment = equipment,
            onEquipmentScreen = onEquipmentScreen::invoke
        )
        BaseDefaultText(
            text = textRelicParts,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun EquipmentImage(
    modifier: Modifier = Modifier,
    equipment: Equipment?,
    contentScale: ContentScale = ContentScale.Fit,
    onEquipmentScreen: () -> Unit,
) {
    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(width = 2.dp, color = GreyTransparent20, shape = RoundedCornerShape(16.dp))
            .clickable {
                onEquipmentScreen()
            },
        model = equipment?.image ?: R.drawable.ic_add,
        contentDescription = null,
        colorFilter = if (equipment == null) ColorFilter.tint(MaterialTheme.colorScheme.secondary) else null,
        contentScale = contentScale,
    )
}