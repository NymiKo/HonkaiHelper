package com.example.tanorami.info_about_hero.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_build_hero.ui.components.HeaderCategory
import com.example.tanorami.core.ui.base_components.text.BaseDefaultText
import com.example.tanorami.core.ui.theme.AppTheme
import com.example.tanorami.core.ui.theme.DarkGrey
import com.example.tanorami.core.ui.theme.Orange
import com.example.tanorami.info_about_hero.data.model.Ability

@Composable
fun AbilitiesListColumn(
    modifier: Modifier = Modifier,
    abilitiesList: List<Ability>,
) {
    HeaderCategory(
        modifier = modifier.padding(top = 24.dp),
        headerCategory = R.string.abilities
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        abilitiesList.forEach { ability ->
            AbilityItem(
                abilityImage = ability.image,
                abilityType = ability.type,
                abilityTitle = ability.title,
                abilityDescription = ability.description
            )
        }
    }
}

@Composable
private fun AbilityItem(
    modifier: Modifier = Modifier,
    abilityImage: String,
    abilityType: String,
    abilityTitle: String,
    abilityDescription: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, DarkGrey, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(DarkGrey, CircleShape)
                    .align(Alignment.CenterVertically)
                    .padding(2.dp),
                model = abilityImage,
                contentDescription = null
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                BaseDefaultText(
                    text = abilityType,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange,
                )

                BaseDefaultText(
                    text = abilityTitle,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        BaseDefaultText(
            text = abilityDescription,
            fontSize = 16.sp,
            lineHeight = 18.sp
        )
    }
}

@Preview
@Composable
private fun AbilityItemPreview() {
    AppTheme {
        AbilityItem(
            abilityImage = "",
            abilityType = "Базовая атака | Одиночная атака",
            abilityTitle = "Спектральный луч",
            abilityDescription = "Наносит выбранному противнику огненный урон, равный 50-130% от силы атаки Асты."
        )
    }
}