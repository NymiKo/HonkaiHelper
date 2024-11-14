package com.example.tanorami.create_build_hero.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.domain.repository.equipment.Equipment
import com.example.core.ui.theme.Blue
import com.example.core.ui.theme.Orange
import com.example.core.ui.theme.Violet
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenEvents
import com.example.tanorami.create_build_hero.presentation.models.EquipmentType

@Composable
fun EquipmentItem(
    modifier: Modifier = Modifier,
    equipment: Equipment,
    equipmentType: EquipmentType,
    onEvent: (CreateBuildHeroScreenEvents) -> Unit,
    hideBottomSheet: () -> Unit,
) {
    AsyncImage(
        modifier = modifier
            .width(80.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                when (equipment.rarity) {
                    0 -> Blue
                    1 -> Violet
                    2 -> Orange
                    else -> Orange
                },
                RoundedCornerShape(10.dp)
            )
            .clickable {
                when (equipmentType) {
                    EquipmentType.WEAPON -> onEvent(
                        CreateBuildHeroScreenEvents.AddWeapon(equipment)
                    )

                    EquipmentType.RELIC_TWO_PARTS -> onEvent(
                        CreateBuildHeroScreenEvents.AddTwoPartsRelic(
                            equipment
                        )
                    )

                    EquipmentType.RELIC_FOUR_PARTS -> onEvent(
                        CreateBuildHeroScreenEvents.AddFourPartsRelic(
                            equipment
                        )
                    )

                    EquipmentType.DECORATION -> onEvent(
                        CreateBuildHeroScreenEvents.AddDecoration(equipment)
                    )
                }
                hideBottomSheet()
            },
        model = equipment.image,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}