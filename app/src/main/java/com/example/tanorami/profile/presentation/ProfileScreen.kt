package com.example.tanorami.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.core.theme.Blue
import com.example.tanorami.core.theme.White
import com.example.tanorami.profile.presentation.components.ProfileTopAppBar
import com.example.tanorami.profile.presentation.components.TeamsAndBuildsInProfile
import com.example.tanorami.profile.presentation.components.UserNotLoggedComponent

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    onChangeNicknameScreen: (nickname: String) -> Unit,
    onEditBuildHeroScreen: (idBuild: Long) -> Unit,
    onEditTeamScreen: (idTeam: Long) -> Unit,
    onLoginScreen: () -> Unit,
) {
    ProfileScreenContent(
        modifier = modifier,
        uiState = viewModel.profileUiState,
        onEvents = { event ->
            when (event) {
                ProfileScreenEvents.ChangeAvatar -> {}
                ProfileScreenEvents.OnChangeNicknameScreen -> onChangeNicknameScreen(viewModel.profileUiState.profileData.nickname)
                is ProfileScreenEvents.OnEditBuildHeroScreen -> onEditBuildHeroScreen(event.idBuild)
                is ProfileScreenEvents.OnEditTeamScreen -> onEditTeamScreen(event.idTeam)
                ProfileScreenEvents.OnLoginScreen -> onLoginScreen()
                else -> Unit
            }
            viewModel.onEvent(event)
        },
    )
}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    uiState: ProfileScreenUiState,
    onEvents: (event: ProfileScreenEvents) -> Unit,
) {
    if (uiState.isAuthorized) {
        Scaffold(modifier = modifier.background(MaterialTheme.colorScheme.background), topBar = {
            ProfileTopAppBar(
                onEditNicknameScreen = { onEvents(ProfileScreenEvents.OnChangeNicknameScreen) },
                logoutAccountClick = { onEvents(ProfileScreenEvents.LogoutAccount) }
            )
        }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(30.dp),
            ) {
                AvatarAndNickname(
                    avatarUrl = uiState.profileData.avatarUrl ?: "",
                    nickname = uiState.profileData.nickname
                )

                TeamsAndBuildsInProfile(
                    heroesBuildsList = uiState.profileData.buildsHeroes,
                    teamsList = uiState.profileData.teamsList,
                    onEditBuildHeroScreen = { onEvents(ProfileScreenEvents.OnEditBuildHeroScreen(it)) },
                    onEditTeamScreen = { onEvents(ProfileScreenEvents.OnEditTeamScreen(it)) },
                )
            }
        }
    } else {
        UserNotLoggedComponent(
            onLoginScreen = { onEvents(ProfileScreenEvents.OnLoginScreen) }
        )
    }
}

@Composable
private fun AvatarAndNickname(
    modifier: Modifier = Modifier, avatarUrl: String, nickname: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AvatarImage(avatarUrl = avatarUrl)
        NicknameText(nickname = nickname)
    }
}

@Composable
private fun AvatarImage(
    modifier: Modifier = Modifier, avatarUrl: String
) {
    Box(
        modifier = modifier.size(100.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp)),
            model = avatarUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        AsyncImage(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .align(Alignment.BottomEnd)
                .background(White)
                .shadow(0.3.dp, CircleShape),
            model = R.drawable.ic_add,
            colorFilter = ColorFilter.tint(color = Blue),
            contentDescription = null
        )
    }
}

@Composable
private fun NicknameText(
    modifier: Modifier = Modifier,
    nickname: String,
) {
    BaseDefaultText(
        modifier = modifier.fillMaxWidth(),
        text = nickname
    )
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreenContent(uiState = ProfileScreenUiState(), onEvents = {})
}

