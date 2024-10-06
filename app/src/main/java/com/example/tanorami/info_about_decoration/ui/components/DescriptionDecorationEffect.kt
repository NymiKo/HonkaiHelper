package com.example.tanorami.info_about_decoration.ui.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.core.theme.DarkGray

@Composable
internal fun DescriptionDecorationEffect(
    modifier: Modifier = Modifier,
    descriptionDecorationEffect: String?,
) {
    Card(
        modifier = modifier.padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(width = 1.dp, color = DarkGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BaseDefaultText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.effect_relic),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            BaseDefaultText(
                modifier = Modifier.fillMaxWidth(),
                text = descriptionDecorationEffect ?: "",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}