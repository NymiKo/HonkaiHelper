package com.example.tanorami.base_build_hero.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.strings.R

@Composable
internal inline fun <T> BestEquipmentsLazyRow(
    modifier: Modifier = Modifier,
    equipmentsList: List<T>,
    crossinline equipmentImage: @Composable (equipment: T) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        itemsIndexed(equipmentsList) { index, equipment ->
            Row(
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                equipmentImage(equipment)

                if (index != equipmentsList.lastIndex) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(15.dp)
                            .height(25.dp)
                            .align(Alignment.CenterVertically),
                        model = R.drawable.ic_arrow_forward,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary)
                    )
                }
            }
        }
    }
}