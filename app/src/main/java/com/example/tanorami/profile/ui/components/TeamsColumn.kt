package com.example.tanorami.profile.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseHeroAvatarAndName
import com.example.tanorami.base_components.lazy_column.BaseLazyColumn
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.core.theme.Blue500
import com.example.tanorami.core.theme.GreyTransparent20
import com.example.tanorami.core.theme.White
import com.example.tanorami.teams.data.model.TeamHero

@Composable
fun TeamsColumn(
    modifier: Modifier = Modifier,
    teamsList: List<TeamHero>,
    onEditTeamScreen: (idTeam: Long) -> Unit,
) {
    BaseLazyColumn(modifier = modifier) {
        items(count = teamsList.size, key = { teamsList[it].idTeam }) {
            TeamItem(
                team = teamsList[it],
                onEditTeamScreen = onEditTeamScreen::invoke
            )
        }
    }
}

@Composable
fun TeamItem(
    modifier: Modifier = Modifier,
    team: TeamHero,
    clickable: Boolean = true,
    onEditTeamScreen: (idTeam: Long) -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = GreyTransparent20,
            )
            .clickable(clickable) {
                onEditTeamScreen(team.idTeam)
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Blue500),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = if (team.nickname.isNullOrEmpty()) 16.dp else 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                BaseHeroAvatarAndName(hero = team.heroOne)
                BaseHeroAvatarAndName(hero = team.heroTwo)
                BaseHeroAvatarAndName(hero = team.heroThree)
                BaseHeroAvatarAndName(hero = team.heroFour)
            }

            if (!team.nickname.isNullOrEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BaseDefaultText(
                        text = stringResource(id = R.string.team_from, team.nickname),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = White,
                    )

                    AsyncImage(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(White, CircleShape),
                        model = if (team.avatar.isNullOrEmpty()) R.drawable.ic_person else team.avatar,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.weight(1F))

                    AsyncImage(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                val clipboard =
                                    context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                                val clipData: ClipData = ClipData.newPlainText("UID", team.uid)
                                clipboard.setPrimaryClip(clipData)
                            },
                        model = R.drawable.ic_baseline_content_copy,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(White)
                    )
                }
            }
        }
    }
}

