package com.example.tanorami.navigation.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CenterFocusWeak
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
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
import com.example.tanorami.profile.ui.ProfileScreen

enum class MainScreens(val route: String) {
    HeroesList("heroes_list"),
    WeaponsList("weapons_list"),
    CreateDialog("create_dialog"),
    TeamsAndBuilds("teams_and_builds"),
    Profile("profile")
}

@Composable
fun MainScreen(
    profileAvatar: String,
    viewModelFactory: ViewModelProvider.Factory
) {
    val navController = rememberNavController()
    val bottomNavigationHeight = 75.dp
    val items = MainScreens.entries.toTypedArray()

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            modifier = Modifier
                .padding(bottom = bottomNavigationHeight)
                .fillMaxHeight(),
            navController = navController,
            startDestination = MainScreens.HeroesList.route,
        ) {
            composable(MainScreens.HeroesList.route) {
                HeroesListScreen(
                    viewModelFactory = viewModelFactory,
                    navController = navController
                )
            }

            composable(MainScreens.WeaponsList.route) {
                Box(modifier = Modifier.fillMaxSize()) {
                    BaseDefaultText(text = "Экран списка оружия")
                }
            }

            composable(MainScreens.TeamsAndBuilds.route) {
                Box(modifier = Modifier.fillMaxSize()) {
                    BaseDefaultText(text = "Экран списков команд и сборок")
                }
            }

            composable(MainScreens.Profile.route) {
                ProfileScreen(
                    viewModelFactory = viewModelFactory,
                    navController = navController
                )
            }
        }

        BottomNavigation(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(bottomNavigationHeight),
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
                            Box(
                                modifier = Modifier
                                    .size(35.dp)
                                    .clip(CircleShape)
                                    .border(if (isSelected) 2.dp else 0.dp, Orange, CircleShape)
                                    .padding(animatedPadding)
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(
                                            MaterialTheme.colorScheme.secondary,
                                            CircleShape
                                        ),
                                    model = profileAvatar.ifEmpty { R.drawable.ic_person },
                                    contentDescription = null,
                                    colorFilter = if (profileAvatar.isEmpty()) ColorFilter.tint(
                                        MaterialTheme.colorScheme.onSecondary
                                    ) else null,
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        } else {
                            Icon(
                                imageVector = when (screen) {
                                    MainScreens.HeroesList -> Icons.Default.Person
                                    MainScreens.WeaponsList -> Icons.Default.CenterFocusWeak
                                    MainScreens.CreateDialog -> Icons.Filled.AddCircle
                                    MainScreens.TeamsAndBuilds -> Icons.Default.Group
                                    else -> Icons.Default.Person
                                },
                                contentDescription = screen.route,
                                tint = if (isSelected) Orange else MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                )
            }
        }
    }
}