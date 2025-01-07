package com.example.tanorami.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tanorami.auth.login.ui.LoginRoute
import com.example.tanorami.auth.login.ui.LoginScreen
import com.example.tanorami.auth.registration.ui.RegistrationRoute
import com.example.tanorami.auth.registration.ui.RegistrationScreen
import com.example.tanorami.base_build_hero.ui.BaseBuildHeroNavArguments
import com.example.tanorami.base_build_hero.ui.BaseBuildHeroScreen
import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersNavArguments
import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersScreen
import com.example.tanorami.change_nickname.ui.ChangeNicknameNavArguments
import com.example.tanorami.change_nickname.ui.ChangeNicknameScreen
import com.example.tanorami.create_build_hero.ui.CreateBuildForHeroNavArguments
import com.example.tanorami.create_build_hero.ui.CreateBuildHeroScreen
import com.example.tanorami.create_build_heroes_list.ui.CreateBuildHeroesListNavArguments
import com.example.tanorami.create_build_heroes_list.ui.CreateBuildHeroesListScreen
import com.example.tanorami.createteam.ui.CreateTeamNavArguments
import com.example.tanorami.createteam.ui.CreateTeamScreen
import com.example.tanorami.info_about_decoration.ui.InfoAboutDecorationNavArguments
import com.example.tanorami.info_about_decoration.ui.InfoAboutDecorationScreen
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroNavArguments
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroScreen
import com.example.tanorami.info_about_relic.ui.InfoAboutRelicNavArgument
import com.example.tanorami.info_about_relic.ui.InfoAboutRelicScreen
import com.example.tanorami.info_about_weapon.ui.InfoAboutWeaponNavArguments
import com.example.tanorami.info_about_weapon.ui.InfoAboutWeaponScreen
import com.example.tanorami.load_data.ui.LoadDataNavArguments
import com.example.tanorami.load_data.ui.LoadDataScreen
import com.example.tanorami.main.ui.MainRoute
import com.example.tanorami.main.ui.MainScreen
import com.example.tanorami.send_feedback.ui.SendFeedbackRoute
import com.example.tanorami.send_feedback.ui.SendFeedbackScreen
import com.example.tanorami.settings.ui.SettingsRoute
import com.example.tanorami.settings.ui.SettingsScreen
import com.example.tanorami.teams.ui.TeamsFromUsersNavArguments
import com.example.tanorami.teams.ui.TeamsFromUsersScreen
import com.example.tanorami.viewing_users_build.ui.ViewingBuildHeroFromUserNavArguments
import com.example.tanorami.viewing_users_build.ui.ViewingBuildHeroFromUserScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModelFactory: ViewModelProvider.Factory
) {
    SharedTransitionLayout {
        CompositionLocalProvider(value = LocalSharedTransitionScope provides this) {
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                navController = navController,
                startDestination = MainRoute
            ) {
                composable<MainRoute> {
                    MainScreen(
                        viewModelFactory = viewModelFactory,
                        rootNavController = navController,
                    )
                }

                composable<LoadDataNavArguments> { backStackEntry ->
                    val loadDataNavArguments: LoadDataNavArguments = backStackEntry.toRoute()
                    LoadDataScreen(
                        navArguments = loadDataNavArguments,
                        viewModelFactory = viewModelFactory,
                        navController = navController,
                    )
                }

                composable<InfoAboutHeroNavArguments>(
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        ) + fadeIn(animationSpec = tween(easing = LinearEasing))
                    },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                        ) + fadeOut(animationSpec = tween(easing = LinearEasing))
                    },
                ) { backStackEntry ->
                    val infoAboutHeroNavArguments: InfoAboutHeroNavArguments =
                        backStackEntry.toRoute()
                    InfoAboutHeroScreen(
                        navArguments = infoAboutHeroNavArguments,
                        viewModelFactory = viewModelFactory,
                        navController = navController,
                    )
                }

                composable<SettingsRoute>(
                    enterTransition = { fadeIn(tween(400)) },
                    exitTransition = { fadeOut(tween(400)) },
                ) {
                    SettingsScreen(
                        viewModelFactory = viewModelFactory,
                        navController = navController
                    )
                }

                composable<BaseBuildHeroNavArguments>(
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            initialOffset = { it / 100 * 20 }
                        ) + fadeIn(animationSpec = tween(300, easing = LinearEasing))
                    },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            targetOffset = { it / 100 * 20 }
                        ) + fadeOut(animationSpec = tween(300, easing = LinearEasing))
                    },
                ) { backStackEntry ->
                    val baseBuildHeroNavArguments: BaseBuildHeroNavArguments =
                        backStackEntry.toRoute()
                    CompositionLocalProvider(value = LocalNavAnimatedVisibilityScope provides this@composable) {
                        BaseBuildHeroScreen(
                            navArguments = baseBuildHeroNavArguments,
                            viewModelFactory = viewModelFactory,
                            navController = navController,
                        )
                    }
                }

                composable<BuildsHeroFromUsersNavArguments>(
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            initialOffset = { it / 100 * 20 }
                        ) + fadeIn(animationSpec = tween(300, easing = LinearEasing))
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            targetOffset = { it / 100 * 20 }
                        ) + fadeOut(animationSpec = tween(300, easing = LinearEasing))
                    },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                ) { backStackEntry ->
                    val buildsHeroFromUsersNavArguments: BuildsHeroFromUsersNavArguments =
                        backStackEntry.toRoute()
                    BuildsHeroFromUsersScreen(
                        navArguments = buildsHeroFromUsersNavArguments,
                        viewModelFactory = viewModelFactory,
                        navController = navController,
                    )
                }

                composable<ViewingBuildHeroFromUserNavArguments>(
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            initialOffset = { it / 100 * 20 }
                        ) + fadeIn(animationSpec = tween(300, easing = LinearEasing))
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            targetOffset = { it / 100 * 20 }
                        ) + fadeOut(animationSpec = tween(300, easing = LinearEasing))
                    },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                ) { backStackEntry ->
                    val viewingBuildHeroFromUserNavArguments: ViewingBuildHeroFromUserNavArguments =
                        backStackEntry.toRoute()
                    ViewingBuildHeroFromUserScreen(
                        navArguments = viewingBuildHeroFromUserNavArguments,
                        viewModelFactory = viewModelFactory,
                        navController = navController,
                    )
                }

                composable<TeamsFromUsersNavArguments>(
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            initialOffset = { it / 100 * 20 }
                        ) + fadeIn(animationSpec = tween(300, easing = LinearEasing))
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            targetOffset = { it / 100 * 20 }
                        ) + fadeOut(animationSpec = tween(300, easing = LinearEasing))
                    },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                ) { backStackEntry ->
                    val teamsFromUsersNavArguments: TeamsFromUsersNavArguments =
                        backStackEntry.toRoute()
                    TeamsFromUsersScreen(
                        navArguments = teamsFromUsersNavArguments,
                        viewModelFactory = viewModelFactory,
                        navController = navController
                    )
                }

                composable<InfoAboutWeaponNavArguments>(
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            initialOffset = { it / 100 * 20 }
                        ) + fadeIn(animationSpec = tween(300, easing = LinearEasing))
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            targetOffset = { it / 100 * 20 }
                        ) + fadeOut(animationSpec = tween(300, easing = LinearEasing))
                    },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                ) { backStackEntry ->
                    val infoAboutWeaponNavArguments: InfoAboutWeaponNavArguments =
                        backStackEntry.toRoute()
                    CompositionLocalProvider(value = LocalNavAnimatedVisibilityScope provides this@composable) {
                        InfoAboutWeaponScreen(
                            navArguments = infoAboutWeaponNavArguments,
                            viewModelFactory = viewModelFactory,
                            navController = navController,
                        )
                    }
                }

                composable<InfoAboutRelicNavArgument>(
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            initialOffset = { it / 100 * 20 }
                        ) + fadeIn(animationSpec = tween(300, easing = LinearEasing))
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            targetOffset = { it / 100 * 20 }
                        ) + fadeOut(animationSpec = tween(300, easing = LinearEasing))
                    },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                ) { backStackEntry ->
                    val infoAboutRelicNavArgument: InfoAboutRelicNavArgument =
                        backStackEntry.toRoute()
                    CompositionLocalProvider(value = LocalNavAnimatedVisibilityScope provides this@composable) {
                        InfoAboutRelicScreen(
                            navArguments = infoAboutRelicNavArgument,
                            viewModelFactory = viewModelFactory,
                            navController = navController
                        )
                    }
                }

                composable<InfoAboutDecorationNavArguments>(
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            initialOffset = { it / 100 * 20 }
                        ) + fadeIn(animationSpec = tween(300, easing = LinearEasing))
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            targetOffset = { it / 100 * 20 }
                        ) + fadeOut(animationSpec = tween(300, easing = LinearEasing))
                    },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                ) { backStackEntry ->
                    val infoAboutDecorationNavArguments: InfoAboutDecorationNavArguments =
                        backStackEntry.toRoute()
                    CompositionLocalProvider(value = LocalNavAnimatedVisibilityScope provides this@composable) {
                        InfoAboutDecorationScreen(
                            navArguments = infoAboutDecorationNavArguments,
                            viewModelFactory = viewModelFactory,
                            navController = navController
                        )
                    }
                }

                composable<CreateBuildForHeroNavArguments>(
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start
                        ) +
                                fadeIn(animationSpec = tween(300, easing = LinearEasing))
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            targetOffset = { it / 100 * 20 }
                        ) + fadeOut(animationSpec = tween(300, easing = LinearEasing))
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End
                        ) + fadeOut(animationSpec = tween(300, easing = LinearEasing))
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            initialOffset = { it / 100 * 20 }) +
                                fadeIn(animationSpec = tween(300, easing = LinearEasing))
                    }
                ) { backStackEntry ->
                    val createBuildForHeroNavArguments: CreateBuildForHeroNavArguments =
                        backStackEntry.toRoute()
                    CreateBuildHeroScreen(
                        navArguments = createBuildForHeroNavArguments,
                        viewModelFactory = viewModelFactory,
                        navController = navController,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable,
                    )
                }

                composable<CreateTeamNavArguments> { backStackEntry ->
                    val createTeamNavArguments: CreateTeamNavArguments = backStackEntry.toRoute()
                    CreateTeamScreen(
                        navArguments = createTeamNavArguments,
                        viewModelFactory = viewModelFactory,
                        navController = navController
                    )
                }

                composable<ChangeNicknameNavArguments> { backStackEntry ->
                    val changeNicknameNavArguments: ChangeNicknameNavArguments =
                        backStackEntry.toRoute()
                    ChangeNicknameScreen(
                        navArguments = changeNicknameNavArguments,
                        viewModelFactory = viewModelFactory,
                        onBack = navController::popBackStack,
                    )
                }

                composable<LoginRoute> {
                    LoginScreen(viewModelFactory = viewModelFactory, navController = navController)
                }

                composable<RegistrationRoute> {
                    RegistrationScreen(
                        viewModelFactory = viewModelFactory,
                        navController = navController
                    )
                }

                composable<SendFeedbackRoute> {
                    SendFeedbackScreen(
                        viewModelFactory = viewModelFactory,
                        navController = navController
                    )
                }

                composable<CreateBuildHeroesListNavArguments> {
                    CreateBuildHeroesListScreen(
                        viewModelFactory = viewModelFactory,
                        navController = navController
                    )
                }
            }
        }
    }
}