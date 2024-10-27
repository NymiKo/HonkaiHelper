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
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroScreen
import com.example.tanorami.info_about_hero.ui.models.InfoAboutHeroNavArguments
import com.example.tanorami.load_data.ui.LoadDataScreen
import com.example.tanorami.main.ui.MainScreen
import com.example.tanorami.settings.ui.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object Main

@Serializable
data class LoadData(val remoteVersionDB: String)

@Serializable
object Settings

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
        startDestination = Main
    ) {
        composable<Main> {
            MainScreen(
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

        composable<Settings> {
            SettingsScreen(
                viewModelFactory = viewModelFactory,
                navController = navController
            )
        }
    }
}