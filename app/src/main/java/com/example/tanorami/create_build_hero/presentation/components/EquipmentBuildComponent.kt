package com.example.tanorami.create_build_hero.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.core.theme.GreyTransparent20

@Composable
fun EquipmentBuildComponent(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CategoryWeapon(weaponImage = R.drawable.ic_add)
        CategoryRelics(relicTwoPartsImage = R.drawable.ic_add, relicFourPartsImage = R.drawable.ic_add)
        CategoryDecoration(decorationImage = R.drawable.ic_add)
    }
}

@Composable
fun CategoryWeapon(
    modifier: Modifier = Modifier,
    weaponImage: Int,
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
                    .width(80.dp), model = weaponImage
            )
        }
    }
}

@Composable
fun CategoryRelics(
    modifier: Modifier = Modifier,
    relicTwoPartsImage: Int,
    relicFourPartsImage: Int,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EquipmentCategoryText(categoryText = stringResource(id = R.string.relic))
        RelicImage(
            relicImage = relicTwoPartsImage,
            textRelicParts = stringResource(id = R.string.relic_two_parts)
        )
        RelicImage(
            relicImage = relicFourPartsImage,
            textRelicParts = stringResource(id = R.string.relic_four_parts)
        )
    }
}

@Composable
fun CategoryDecoration(
    modifier: Modifier = Modifier,
    decorationImage: Int,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EquipmentCategoryText(categoryText = stringResource(id = R.string.decoration))
        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
            EquipmentImage(modifier = Modifier.size(100.dp), model = decorationImage)
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
    relicImage: Int,
    textRelicParts: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EquipmentImage(modifier = Modifier.size(80.dp), model = relicImage)
        BaseDefaultText(
            text = textRelicParts,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun EquipmentImage(
    modifier: Modifier = Modifier,
    model: Int,
) {
    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(width = 2.dp, color = GreyTransparent20, shape = RoundedCornerShape(16.dp)),
        model = model,
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EquipmentBuildComponentPreview(modifier: Modifier = Modifier) {
    AppTheme {
        EquipmentBuildComponent()
    }
}