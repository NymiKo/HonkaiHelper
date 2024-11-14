package com.example.tanorami.base_build_hero.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ui.base_components.text.BaseDefaultText

@Composable
internal fun HeaderCategory(
    modifier: Modifier = Modifier,
    headerCategory: Int,
) {
    BaseDefaultText(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        text = stringResource(id = headerCategory),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}