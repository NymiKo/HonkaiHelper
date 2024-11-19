package com.example.tanorami.info_about_weapon.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ui.theme.DarkGrey
import com.example.strings.R

@Composable
internal fun DescriptionWeaponSkill(
    modifier: Modifier = Modifier,
    descriptionWeaponSkill: String?,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(width = 1.dp, DarkGrey)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            com.example.ui.components.text.BaseDefaultText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.skill_weapon),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )

            com.example.ui.components.text.BaseDefaultText(
                modifier = Modifier.fillMaxWidth(),
                text = descriptionWeaponSkill ?: "",
                fontSize = 17.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}