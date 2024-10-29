package com.example.tanorami.main.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.example.tanorami.R
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.heroes.ui.HeroesListScreen
import com.example.tanorami.load_data.ui.LoadDataNavArguments
import com.example.tanorami.main.presentation.MainScreenViewModel
import com.example.tanorami.main.presentation.models.MainScreenEvents
import com.example.tanorami.main.presentation.models.MainScreenSideEffects
import com.example.tanorami.main.presentation.models.MainScreenUiState
import com.example.tanorami.profile.ui.ProfileScreen
import com.example.tanorami.teams_and_builds.ui.TeamsAndBuildsScreen
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

    MainScreenContent(
        uiState = state,
        viewModelFactory = viewModelFactory,
        rootNavController = rootNavController,
        onEvent = viewModel::onEvent
    )

    if (sideEffect is MainScreenSideEffects.OnLoadDataScreen) {
        rootNavController.navigate(LoadDataNavArguments(remoteVersionDB = sideEffect.remoteVersionDB))
        viewModel.clearEffect()
    }
}

@Composable
private fun MainScreenContent(
    uiState: MainScreenUiState,
    viewModelFactory: ViewModelProvider.Factory,
    rootNavController: NavController,
    onEvent: (MainScreenEvents) -> Unit,
) {
    val navController = rememberNavController()
    val items = MainScreens.entries.toTypedArray()

    if (uiState.dialogVisibilityState) {
        AlertDialog(
            title = {
                BaseDefaultText(
                    text = stringResource(id = R.string.data_needs_updated),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            },
            text = { BaseDefaultText(text = uiState.message) },
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = { onEvent(MainScreenEvents.DialogButtonOkClick) }) {
                    BaseDefaultText(text = stringResource(id = R.string.ok))
                }
            },
            icon = {
                Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Orange)
            },
            containerColor = MaterialTheme.colorScheme.background,
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
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background),
            navController = navController,
            startDestination = MainScreens.HeroesList.route,
        ) {
            composable(MainScreens.HeroesList.route) {
                HeroesListScreen(
                    viewModelFactory = viewModelFactory,
                    navController = rootNavController
                )
            }

            composable(MainScreens.WeaponsList.route) {
                Box(modifier = Modifier.fillMaxSize()) {
                    BaseDefaultText(text = "Экран списка оружия")
                }
            }

            composable(MainScreens.TeamsAndBuilds.route) {
                TeamsAndBuildsScreen(
                    viewModelFactory = viewModelFactory,
                    navController = rootNavController
                )
            }

            composable(MainScreens.Profile.route) {
                ProfileScreen(
                    viewModelFactory = viewModelFactory,
                    navController = rootNavController
                )
            }
        }
    }
}