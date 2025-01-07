package com.example.profile.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.base.injectedViewModel
import com.example.profile.di.DaggerProfileComponent
import com.example.profile.di.ProfileComponentDependenciesProvider
import com.example.profile.presentation.ProfileViewModel
import com.example.profile.ui.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
object ProfileRoute

fun NavController.navigateToProfileScreen(navOptions: NavOptions) =
    navigate(route = ProfileRoute, navOptions)

fun NavGraphBuilder.profileScreen(
    onChangeNicknameScreen: (nickname: String) -> Unit,
    onEditBuildHeroScreen: (idBuild: Long) -> Unit,
    onEditTeamScreen: (idTeam: Long) -> Unit,
    onLoginScreen: () -> Unit,
) {
    composable(route = "profile") {
        val profileComponentDependencies =
            (LocalContext.current.applicationContext as ProfileComponentDependenciesProvider).getProfileComponentDependencies()
        val component = DaggerProfileComponent.factory().create(profileComponentDependencies)
        val viewModel: ProfileViewModel = injectedViewModel { component.getViewModel() }

        ProfileScreen(
            viewModel = viewModel,
            onChangeNicknameScreen = onChangeNicknameScreen::invoke,
            onEditBuildHeroScreen = onEditBuildHeroScreen::invoke,
            onEditTeamScreen = onEditTeamScreen::invoke,
            onLoginScreen = onLoginScreen::invoke,
        )
    }
}