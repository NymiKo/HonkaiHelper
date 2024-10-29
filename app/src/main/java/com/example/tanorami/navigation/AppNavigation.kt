package com.example.tanorami.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tanorami.auth.login.ui.LoginRoute
import com.example.tanorami.auth.login.ui.LoginScreen
import com.example.tanorami.base_build_hero.ui.BaseBuildHeroNavArguments
import com.example.tanorami.base_build_hero.ui.BaseBuildHeroScreen
import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersNavArguments
import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersScreen
import com.example.tanorami.change_nickname.ui.ChangeNicknameNavArguments
import com.example.tanorami.change_nickname.ui.ChangeNicknameScreen
import com.example.tanorami.create_build_hero.ui.CreateBuildHeroNavArguments
import com.example.tanorami.create_build_hero.ui.CreateBuildHeroScreen
import com.example.tanorami.createteam.ui.CreateTeamNavArguments
import com.example.tanorami.createteam.ui.CreateTeamScreen
import com.example.tanorami.info_about_decoration.ui.InfoAboutDecorationNavArguments
import com.example.tanorami.info_about_decoration.ui.InfoAboutDecorationScreen
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroScreen
import com.example.tanorami.info_about_hero.ui.models.InfoAboutHeroNavArguments
import com.example.tanorami.info_about_relic.ui.InfoAboutRelicNavArgument
import com.example.tanorami.info_about_relic.ui.InfoAboutRelicScreen
import com.example.tanorami.info_about_weapon.ui.InfoAboutWeaponNavArguments
import com.example.tanorami.info_about_weapon.ui.InfoAboutWeaponScreen
import com.example.tanorami.load_data.ui.LoadDataNavArguments
import com.example.tanorami.load_data.ui.LoadDataScreen
import com.example.tanorami.main.ui.MainRoute
import com.example.tanorami.main.ui.MainScreen
import com.example.tanorami.settings.ui.SettingsRoute
import com.example.tanorami.settings.ui.SettingsScreen
import com.example.tanorami.teams.ui.TeamsFromUsersNavArguments
import com.example.tanorami.teams.ui.TeamsFromUsersScreen
import com.example.tanorami.viewing_users_build.ui.ViewingBuildHeroFromUserNavArguments
import com.example.tanorami.viewing_users_build.ui.ViewingBuildHeroFromUserScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModelFactory: ViewModelProvider.Factory
) {
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
                rootNavController = navController
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

        composable<InfoAboutHeroNavArguments> { backStackEntry ->
            val infoAboutHeroNavArguments: InfoAboutHeroNavArguments = backStackEntry.toRoute()
            InfoAboutHeroScreen(
                navArguments = infoAboutHeroNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController,
            )
        }

        composable<SettingsRoute> {
            SettingsScreen(
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }

        composable<BaseBuildHeroNavArguments> { backStackEntry ->
            val baseBuildHeroNavArguments: BaseBuildHeroNavArguments = backStackEntry.toRoute()
            BaseBuildHeroScreen(
                navArguments = baseBuildHeroNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController,
            )
        }

        composable<BuildsHeroFromUsersNavArguments> { backStackEntry ->
            val buildsHeroFromUsersNavArguments: BuildsHeroFromUsersNavArguments =
                backStackEntry.toRoute()
            BuildsHeroFromUsersScreen(
                navArguments = buildsHeroFromUsersNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController,
            )
        }

        composable<ViewingBuildHeroFromUserNavArguments> { backStackEntry ->
            val viewingBuildHeroFromUserNavArguments: ViewingBuildHeroFromUserNavArguments =
                backStackEntry.toRoute()
            ViewingBuildHeroFromUserScreen(
                navArguments = viewingBuildHeroFromUserNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }

        composable<TeamsFromUsersNavArguments> { backStackEntry ->
            val teamsFromUsersNavArguments: TeamsFromUsersNavArguments = backStackEntry.toRoute()
            TeamsFromUsersScreen(
                navArguments = teamsFromUsersNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }

        composable<InfoAboutWeaponNavArguments> { backStackEntry ->
            val infoAboutWeaponNavArguments: InfoAboutWeaponNavArguments = backStackEntry.toRoute()
            InfoAboutWeaponScreen(
                navArguments = infoAboutWeaponNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }

        composable<InfoAboutRelicNavArgument> { backStackEntry ->
            val infoAboutRelicNavArgument: InfoAboutRelicNavArgument = backStackEntry.toRoute()
            InfoAboutRelicScreen(
                navArguments = infoAboutRelicNavArgument,
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }

        composable<InfoAboutDecorationNavArguments> { backStackEntry ->
            val infoAboutDecorationNavArguments: InfoAboutDecorationNavArguments =
                backStackEntry.toRoute()
            InfoAboutDecorationScreen(
                navArguments = infoAboutDecorationNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }

        composable<CreateBuildHeroNavArguments> { backStackEntry ->
            val createBuildHeroNavArguments: CreateBuildHeroNavArguments = backStackEntry.toRoute()
            CreateBuildHeroScreen(
                navArguments = createBuildHeroNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController
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
            val changeNicknameNavArguments: ChangeNicknameNavArguments = backStackEntry.toRoute()
            ChangeNicknameScreen(
                navArguments = changeNicknameNavArguments,
                viewModelFactory = viewModelFactory,
                onBack = navController::popBackStack,
            )
        }

        composable<LoginRoute> {
            LoginScreen(viewModelFactory = viewModelFactory, navController = navController)
        }
    }
}