package com.example.heroes_list.heroes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.core.ui.base_components.text.BaseDefaultText
import com.example.core.ui.theme.Black
import com.example.core.ui.theme.Orange
import com.example.core.ui.theme.Violet
import com.example.core.ui.theme.White
import com.example.domain.repository.hero.model.HeroBaseInfoModel

@androidx.compose.runtime.Composable
fun HeroItem(
    modifier: Modifier = Modifier,
    heroModel: HeroBaseInfoModel,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(
                4.dp,
                if (heroModel.rarity) Orange else Violet,
                RoundedCornerShape(16.dp)
            )
            .aspectRatio(1F)
            .clickable { onClick() },
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .background(White),
            model = heroModel.localAvatarPath,
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    verticalGradient(
                        colorStops = arrayOf(
                            0.5F to Color.Transparent,
                            1F to Black
                        )
                    )
                )
                .align(Alignment.BottomCenter)
        )

        BaseDefaultText(
            modifier = Modifier
                .padding(bottom = 12.dp, start = 4.dp, end = 4.dp)
                .align(Alignment.BottomCenter),
            text = heroModel.name,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            color = White,
            fontWeight = FontWeight.Bold,
        )
    }
}