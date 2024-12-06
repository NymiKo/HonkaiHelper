package com.example.teams_and_builds.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.base.injectedViewModel
import com.example.teams_and_builds.di.DaggerTeamsAndBuildsComponent
import com.example.teams_and_builds.di.TeamsAndBuildsComponentDependenciesProvider
import com.example.teams_and_builds.presentation.TeamsAndBuildsViewModel
import com.example.teams_and_builds.ui.TeamsAndBuildsScreen
import kotlinx.serialization.Serializable

@Serializable
object TeamsAndBuildsScreenRoute

fun NavController.navigateToTeamsAndBuildsScreen(navOptions: NavOptions) =
    navigate(route = TeamsAndBuildsScreenRoute, navOptions)

fun NavGraphBuilder.teamsAndBuildsScreen(
    onBuildHeroClick: (idBuild: Long) -> Unit,
) {
    composable(
        route = "teams_and_builds"
    ) {
        val teamsAndBuildsComponentDependencies =
            (LocalContext.current.applicationContext as TeamsAndBuildsComponentDependenciesProvider).getTeamsAndBuildsComponentDependencies()
        val component =
            DaggerTeamsAndBuildsComponent.factory().create(teamsAndBuildsComponentDependencies)
        val viewModel: TeamsAndBuildsViewModel = injectedViewModel { component.getViewModel() }

        TeamsAndBuildsScreen(
            viewModel = viewModel,
            onBuildHeroClick = onBuildHeroClick::invoke
        )
    }
}