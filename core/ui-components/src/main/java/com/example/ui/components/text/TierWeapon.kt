package com.example.ui.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ui.theme.Black

@Composable
fun TierWeapon(
    modifier: Modifier = Modifier,
    tier: String,
) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .size(20.dp)
            .clip(CircleShape)
            .background(if (tier == "I") Black else Color(0xFFFFCF70), CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = tier,
            color = if (tier == "I") Color(0xFFFFCF70) else Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 16.sp,
        )
    }
}