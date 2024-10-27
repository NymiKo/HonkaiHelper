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
import com.example.tanorami.base_build_hero.ui.BaseBuildHeroNavArguments
import com.example.tanorami.base_build_hero.ui.BaseBuildHeroScreen
import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersNavArguments
import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersScreen
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroScreen
import com.example.tanorami.info_about_hero.ui.models.InfoAboutHeroNavArguments
import com.example.tanorami.load_data.ui.LoadDataNavArguments
import com.example.tanorami.load_data.ui.LoadDataScreen
import com.example.tanorami.main.ui.MainNavArguments
import com.example.tanorami.main.ui.MainScreen
import com.example.tanorami.settings.ui.SettingsNavArguments
import com.example.tanorami.settings.ui.SettingsScreen
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
        startDestination = MainNavArguments
    ) {
        composable<MainNavArguments> {
            MainScreen(
                viewModelFactory = viewModelFactory,
                rootNavController = navController
            )
        }

        composable<LoadDataNavArguments> { backStackEntry ->
            val loadDataNavArguments: LoadDataNavArguments = backStackEntry.toRoute()
            LoadDataScreen(
                loadDataNavArguments = loadDataNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController,
            )
        }

        composable<InfoAboutHeroNavArguments> { backStackEntry ->
            val infoAboutHeroNavArguments: InfoAboutHeroNavArguments = backStackEntry.toRoute()
            InfoAboutHeroScreen(
                infoAboutHeroNavArguments = infoAboutHeroNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController,
            )
        }

        composable<SettingsNavArguments> {
            SettingsScreen(
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }

        composable<BaseBuildHeroNavArguments> { backStackEntry ->
            val baseBuildHeroNavArguments: BaseBuildHeroNavArguments = backStackEntry.toRoute()
            BaseBuildHeroScreen(
                baseBuildHeroNavArguments = baseBuildHeroNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController,
            )
        }

        composable<BuildsHeroFromUsersNavArguments> { backStackEntry ->
            val buildsHeroFromUsersNavArguments: BuildsHeroFromUsersNavArguments =
                backStackEntry.toRoute()
            BuildsHeroFromUsersScreen(
                buildsHeroFromUsersNavArguments = buildsHeroFromUsersNavArguments,
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
    }
}