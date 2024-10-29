package com.example.tanorami.profile.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base.shimmerEffect
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.change_nickname.presentation.ChangeNicknameFragment
import com.example.tanorami.core.theme.Blue
import com.example.tanorami.core.theme.White
import com.example.tanorami.create_build_hero.ui.CreateBuildHeroNavArguments
import com.example.tanorami.createteam.ui.CreateTeamFragment
import com.example.tanorami.profile.presentation.ProfileViewModel
import com.example.tanorami.profile.presentation.components.ErrorComponent
import com.example.tanorami.profile.presentation.components.ProfileTopAppBar
import com.example.tanorami.profile.presentation.components.TeamsAndBuildsInProfile
import com.example.tanorami.profile.presentation.components.UserNotLoggedComponent
import com.example.tanorami.profile.presentation.models.ProfileScreenEvents
import com.example.tanorami.profile.presentation.models.ProfileScreenUiState
import com.example.tanorami.utils.toFile

@Composable
fun ProfileScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: ProfileViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    ProfileScreenContent(
        uiState = viewModel.profileUiState.collectAsState().value,
        onEvents = { event ->
            when (event) {
                ProfileScreenEvents.OnChangeNicknameScreen -> {
                    navController.navigate(
                        R.id.changeNicknameFragment,
                        ChangeNicknameFragment.newInject("")
                    )
                }

                is ProfileScreenEvents.OnEditBuildHeroScreen -> {
                    navController.navigate(route = CreateBuildHeroNavArguments(idBuild = event.idBuild))
                }

                is ProfileScreenEvents.OnEditTeamScreen -> {
                    navController.navigate(
                        R.id.createTeamFragment,
                        CreateTeamFragment.newInstance(event.idTeam)
                    )
                }

                ProfileScreenEvents.OnLoginScreen -> navController.navigate(R.id.loginFragment)
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
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                onEvents(ProfileScreenEvents.UploadAvatarOnServer(uri.toFile(context)))
            }
        }

    when (uiState) {
        is ProfileScreenUiState.Success -> {
            Scaffold(
                modifier = modifier.background(MaterialTheme.colorScheme.background),
                topBar = {
                    ProfileTopAppBar(
                        onEditNicknameScreen = { onEvents(ProfileScreenEvents.OnChangeNicknameScreen) },
                        logoutAccountClick = { onEvents(ProfileScreenEvents.LogoutAccount) }
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                ) {
                    AvatarAndNickname(
                        avatarUrl = uiState.user.avatarUrl ?: "",
                        nickname = uiState.user.nickname,
                        uploadAvatarOnServer = { launcher.launch("image/*") }
                    )

                    TeamsAndBuildsInProfile(
                        heroesBuildsList = uiState.user.buildsHeroes,
                        teamsList = uiState.user.teamsList,
                        onEditBuildHeroScreen = {
                            onEvents(
                                ProfileScreenEvents.OnEditBuildHeroScreen(
                                    it
                                )
                            )
                        },
                        onEditTeamScreen = { onEvents(ProfileScreenEvents.OnEditTeamScreen(it)) },
                    )
                }
            }
        }

        ProfileScreenUiState.NotAuthorized -> {
            UserNotLoggedComponent(
                onLoginScreen = { onEvents(ProfileScreenEvents.OnLoginScreen) }
            )
        }

        ProfileScreenUiState.Loading -> {
            ShimmerLoading()
        }

        is ProfileScreenUiState.Error -> {
            ErrorComponent(loadingProfile = { onEvents(ProfileScreenEvents.FetchProfile) })
        }

        ProfileScreenUiState.Empty -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            )
        }
    }
}

@Composable
private fun AvatarAndNickname(
    modifier: Modifier = Modifier,
    avatarUrl: String,
    nickname: String,
    uploadAvatarOnServer: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AvatarImage(avatarUrl = avatarUrl, uploadAvatarOnServer = uploadAvatarOnServer::invoke)
        NicknameText(nickname = nickname)
    }
}

@Composable
private fun AvatarImage(
    modifier: Modifier = Modifier,
    avatarUrl: String,
    uploadAvatarOnServer: () -> Unit,
) {
    Box(
        modifier = modifier.size(100.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    uploadAvatarOnServer()
                }
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
        text = nickname,
        fontSize = 24.sp,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShimmerLoading(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        TopAppBar(title = { })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect(),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect(),
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreenContent(uiState = ProfileScreenUiState.Loading, onEvents = {})
}

