package com.example.tanorami.base_build_hero.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal inline fun <T> CategoryBestEquipments(
    modifier: Modifier = Modifier,
    equipmentsList: List<T>,
    headerCategory: Int,
    crossinline equipmentImage: @Composable (equipment: T) -> Unit
) {
    Column(modifier = modifier) {
        HeaderCategory(headerCategory = headerCategory)

        BestEquipmentsLazyRow(
            equipmentsList = equipmentsList,
            equipmentImage = { equipment ->
                equipmentImage(equipment)
            }
        )
    }
}