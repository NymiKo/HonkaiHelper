package com.example.tanorami.createteam.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.core.theme.DarkGrey
import com.example.tanorami.core.theme.Green
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.core.theme.Violet
import com.example.tanorami.core.theme.White
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.createteam.presentation.models.CreateTeamScreenEvents
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity

@Composable
fun ItemHeroAvatarWithName(
    modifier: Modifier = Modifier,
    activeHeroInTeam: ActiveHeroInTeam,
    onEvent: (CreateTeamScreenEvents) -> Unit
) {
    Column(
        modifier = modifier
            .height(120.dp)
            .width(120.dp)
            .border(
                border = if (activeHeroInTeam.active) BorderStroke(3.dp, Green)
                else BorderStroke(1.dp, DarkGrey),
                shape = if (activeHeroInTeam.active) RoundedCornerShape(15.dp) else RectangleShape
            )
            .clip(if (activeHeroInTeam.active) RoundedCornerShape(15.dp) else RectangleShape)
            .clickable {
                if (activeHeroInTeam.active) {
                    onEvent(CreateTeamScreenEvents.RemoveHeroFromTeam(activeHeroInTeam))
                } else {
                    onEvent(CreateTeamScreenEvents.AddHeroInTeam(activeHeroInTeam))
                }
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .background(if (activeHeroInTeam.hero.rarity) Orange else Violet),
            model = activeHeroInTeam.hero.localAvatarPath,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        BaseDefaultText(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .background(DarkGrey)
                .wrapContentHeight(),
            text = activeHeroInTeam.hero.name,
            fontSize = 11.sp,
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            lineHeight = 11.sp,
            maxLines = 2,
        )
    }
}

@Preview
@Composable
private fun ItemHeroAvatarWithNamePreview() {
    ItemHeroAvatarWithName(
        activeHeroInTeam = ActiveHeroInTeam(
            HeroWithNameAvatarRarity(1, "", "", false)
        ),
        onEvent = {}
    )
}