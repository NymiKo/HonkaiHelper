package com.example.tanorami.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroScreen
import com.example.tanorami.info_about_hero.ui.models.InfoAboutHeroNavArguments
import com.example.tanorami.load_data.ui.LoadDataScreen
import com.example.tanorami.navigation.main.MainScreen
import kotlinx.serialization.Serializable

@Serializable
object Main

@Serializable
data class LoadData(val remoteVersionDB: String)

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    profileAvatar: String,
    viewModelFactory: ViewModelProvider.Factory
) {
    NavHost(
        navController = navController,
        startDestination = Main
    ) {
        composable<Main> {
            MainScreen(
                profileAvatar = profileAvatar,
                viewModelFactory = viewModelFactory,
                rootNavController = navController
            )
        }

        composable<LoadData> { backStackEntry ->
            val loadDataNavArguments: LoadData = backStackEntry.toRoute()
            LoadDataScreen(
                loadDataNavArguments = loadDataNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }

        composable<InfoAboutHeroNavArguments> { backStackEntry ->
            val infoAboutHeroNavArguments: InfoAboutHeroNavArguments = backStackEntry.toRoute()
            InfoAboutHeroScreen(
                infoAboutHeroNavArguments = infoAboutHeroNavArguments,
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }
    }
}