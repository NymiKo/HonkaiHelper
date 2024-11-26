package com.example.tanorami.main.ui

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.core.ui.theme.Orange
import com.example.heroes_list.heroes.navigation.heroesListScreen
import com.example.tanorami.R
import com.example.tanorami.create_build_heroes_list.ui.CreateBuildHeroesListNavArguments
import com.example.tanorami.createteam.ui.CreateTeamNavArguments
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroNavArguments
import com.example.tanorami.load_data.ui.LoadDataNavArguments
import com.example.tanorami.main.presentation.MainScreenViewModel
import com.example.tanorami.main.presentation.models.MainScreenEvents
import com.example.tanorami.main.presentation.models.MainScreenSideEffects
import com.example.tanorami.main.presentation.models.MainScreenUiState
import com.example.tanorami.main.ui.components.CreateBuildOrTeamDialog
import com.example.tanorami.main.ui.components.UploadingDataDialog
import com.example.tanorami.profile.ui.ProfileScreen
import com.example.tanorami.settings.ui.SettingsRoute
import com.example.tanorami.teams_and_builds.ui.TeamsAndBuildsScreen
import com.example.tanorami.utils.toast
import com.example.tanorami.weapons_list.ui.WeaponsListScreen
import kotlinx.serialization.Serializable

enum class MainScreens(val route: String) {
    HeroesList("heroes_list"),
    WeaponsList("weapons_list"),
    CreateDialog("create_dialog"),
    TeamsAndBuilds("teams_and_builds"),
    Profile("profile")
}

@Serializable
object MainRoute

@Composable
fun MainScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory),
    rootNavController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffect = viewModel.uiEffect().collectAsState(initial = null).value
    val context = LocalContext.current

    MainScreenContent(
        uiState = state,
        viewModelFactory = viewModelFactory,
        rootNavController = rootNavController,
        context = context,
        onEvent = viewModel::onEvent
    )

    when (sideEffect) {
        MainScreenSideEffects.OnCreateTeamScreen -> {
            rootNavController.navigate(route = CreateTeamNavArguments())
            viewModel.clearEffect()
        }

        is MainScreenSideEffects.OnLoadDataScreen -> {
            rootNavController.navigate(LoadDataNavArguments(remoteVersionDB = sideEffect.remoteVersionDB))
            viewModel.clearEffect()
        }

        MainScreenSideEffects.CreateBuildForHeroScreen -> {
            rootNavController.navigate(route = CreateBuildHeroesListNavArguments)
            viewModel.clearEffect()
        }

        is MainScreenSideEffects.ShowToast -> {
            toast(context, sideEffect.message)
            viewModel.clearEffect()
        }

        null -> {}
    }
}

@Composable
private fun MainScreenContent(
    uiState: MainScreenUiState,
    viewModelFactory: ViewModelProvider.Factory,
    rootNavController: NavController,
    context: Context,
    onEvent: (MainScreenEvents) -> Unit,
) {
    val navController = rememberNavController()
    val items = MainScreens.entries.toTypedArray()

    if (uiState.dialogUploadingDataVisibilityState) {
        UploadingDataDialog(
            text = uiState.message,
            onOkButtonClick = { onEvent(MainScreenEvents.DialogUploadingDataButtonOkClick) }
        )
    }

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding()
            .fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true

                    BottomNavigationItem(
                        selected = isSelected,
                        onClick = {
                            if (screen == MainScreens.CreateDialog) {
                                onEvent(
                                    MainScreenEvents.ChangeVisibilityDialogCreateBuildOrTeam(
                                        true
                                    )
                                )
                            } else {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }

                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            if (screen == MainScreens.Profile) {
                                val animatedPadding by animateDpAsState(
                                    targetValue = if (isSelected) 6.dp else 0.dp,
                                    label = "padding"
                                )
                                val animatedBorder by animateDpAsState(
                                    targetValue = if (isSelected) 2.dp else 0.dp,
                                    label = "border"
                                )
                                Box(
                                    modifier = Modifier
                                        .size(35.dp)
                                        .clip(CircleShape)
                                        .border(animatedBorder, Orange, CircleShape)
                                        .padding(animatedPadding)
                                ) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(
                                                MaterialTheme.colorScheme.secondary,
                                                CircleShape
                                            ),
                                        model = uiState.userProfileAvatar.ifEmpty { R.drawable.ic_person },
                                        contentDescription = null,
                                        colorFilter = if (uiState.userProfileAvatar.isEmpty()) ColorFilter.tint(
                                            MaterialTheme.colorScheme.onSecondary
                                        ) else null,
                                        contentScale = ContentScale.Crop,
                                    )
                                }
                            } else {
                                Icon(
                                    modifier = Modifier.size(25.dp),
                                    painter = painterResource(
                                        id = when (screen) {
                                            MainScreens.HeroesList -> R.drawable.icon_heroes
                                            MainScreens.WeaponsList -> R.drawable.icon_light_cone
                                            MainScreens.CreateDialog -> R.drawable.ic_add_circle
                                            MainScreens.TeamsAndBuilds -> R.drawable.icon_data_bank
                                            else -> R.drawable.icon_heroes
                                        }
                                    ),
                                    contentDescription = null,
                                    tint = if (isSelected) Orange else MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            navController = navController,
            startDestination = MainScreens.HeroesList.route,
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { it / 100 * 3 },
                    animationSpec = tween(600)
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(200))
            }
        ) {
            heroesListScreen(
                onHeroClick = { navController.navigate(InfoAboutHeroNavArguments(it)) },
                onSettingsIconClick = { navController.navigate(SettingsRoute) }
            )

            composable(
                route = MainScreens.WeaponsList.route,
            ) {
                WeaponsListScreen(
                    viewModelFactory = viewModelFactory,
                    navController = rootNavController,
                )
            }

            composable(
                route = MainScreens.TeamsAndBuilds.route,
            ) {
                TeamsAndBuildsScreen(
                    viewModelFactory = viewModelFactory,
                    navController = rootNavController,
                )
            }

            composable(
                route = MainScreens.Profile.route,
            ) {
                ProfileScreen(
                    viewModelFactory = viewModelFactory,
                    navController = rootNavController,
                )
            }
        }

        if (uiState.dialogCreateBuildOrTeamVisibilityState) {
            CreateBuildOrTeamDialog(onEvent = onEvent::invoke)
        }
    }
}