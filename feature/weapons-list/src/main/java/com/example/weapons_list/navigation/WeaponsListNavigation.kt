package com.example.weapons_list.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.base.injectedViewModel
import com.example.weapons_list.di.DaggerWeaponsListComponent
import com.example.weapons_list.di.WeaponsListComponentDependenciesProvider
import com.example.weapons_list.presentation.WeaponsListViewModel
import com.example.weapons_list.ui.WeaponsListScreen
import kotlinx.serialization.Serializable

@Serializable
object WeaponsListScreenRoute

fun NavController.navigateToWeaponsListScreen(navOptions: NavOptions) =
    navigate(route = WeaponsListScreenRoute, navOptions)

fun NavGraphBuilder.weaponsListScreen(
    onWeaponClick: (idWeapon: Int) -> Unit,
) {
    composable(
        route = "weapons_list"
    ) {
        val weaponsListComponentDependencies =
            (LocalContext.current.applicationContext as WeaponsListComponentDependenciesProvider).getWeaponsListComponentDependencies()
        val component =
            DaggerWeaponsListComponent.factory().create(weaponsListComponentDependencies)
        val viewModel: WeaponsListViewModel = injectedViewModel {
            component.getViewModel()
        }

        WeaponsListScreen(
            viewModel = viewModel,
            onWeaponClick = onWeaponClick::invoke
        )
    }
}