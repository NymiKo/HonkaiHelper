package com.example.heroes_list.heroes.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.base.injectedViewModel
import com.example.heroes_list.heroes.di.DaggerHeroesListComponent
import com.example.heroes_list.heroes.di.HeroesListComponentDependenciesProvider
import com.example.heroes_list.heroes.presentation.HeroesListViewModel
import com.example.heroes_list.heroes.ui.HeroesListScreen
import kotlinx.serialization.Serializable

@Serializable
object HeroesListScreenRoute

fun NavController.navigateToHeroesListScreen(navOptions: NavOptions) =
    navigate(route = HeroesListScreenRoute, navOptions)

fun NavGraphBuilder.heroesListScreen(
    onHeroClick: (idHero: Int) -> Unit,
    onSettingsIconClick: () -> Unit,
) {
    composable(route = "heroes_list") {
        val heroesListComponentDependencies =
            (LocalContext.current.applicationContext as HeroesListComponentDependenciesProvider).getHeroesListComponentDependencies()
        val component = DaggerHeroesListComponent.builder()
            .heroesListComponentDependencies(heroesListComponentDependencies).build()
        val viewModel: HeroesListViewModel = injectedViewModel {
            component.getViewModel()
        }

        HeroesListScreen(
            viewModel = viewModel,
            onHeroClick = onHeroClick::invoke,
            onSettingsIconClick = onSettingsIconClick::invoke
        )
    }
}