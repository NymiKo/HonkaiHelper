package com.example.tanorami.info_about_relic.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tanorami.R
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.core.theme.DarkGrey
import com.example.tanorami.core.theme.Orange

@Composable
internal fun DescriptionRelicEffect(
    modifier: Modifier = Modifier,
    descriptionTwoRelicEffect: String?,
    descriptionFourRelicEffect: String?,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(width = 1.dp, color = DarkGrey)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BaseDefaultText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.effect_relic),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )

            DescriptionEffect(
                textRelic = stringResource(id = R.string.effect_relic_two_parts),
                descriptionEffect = descriptionTwoRelicEffect
            )

            DescriptionEffect(
                textRelic = stringResource(id = R.string.effect_relic_four_parts),
                descriptionEffect = descriptionFourRelicEffect
            )
        }
    }
}

@Composable
private fun DescriptionEffect(
    textRelic: String,
    descriptionEffect: String?,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Orange)){
                append(textRelic)
            }
            append(" $descriptionEffect")
        },
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        color = MaterialTheme.colorScheme.secondary,
    )
}