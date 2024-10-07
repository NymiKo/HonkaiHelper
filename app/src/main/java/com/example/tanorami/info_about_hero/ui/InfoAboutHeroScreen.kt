package com.example.tanorami.info_about_hero.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.base_build_hero.ui.BaseBuildHeroFragment
import com.example.tanorami.info_about_hero.presentation.InfoAboutHeroViewModel
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenEvents
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenSideEffects
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenUiState
import com.example.tanorami.teams.TeamsListFragment
import com.example.tanorami.utils.OnLifecycleEvent

@Composable
fun InfoAboutHeroScreen(
    idHero: Int,
    viewModel: InfoAboutHeroViewModel,
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsStateWithLifecycle().value
    val sideEffects = viewModel.uiEffect().collectAsStateWithLifecycle(initialValue = null).value

    InfoAboutHeroScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when(sideEffects) {
        InfoAboutHeroScreenSideEffects.OnBack -> {
            navController.popBackStack()
        }
        is InfoAboutHeroScreenSideEffects.OnBaseBuildHeroScreen -> {
            navController.navigate(
                R.id.action_infoAboutHeroFragment_to_baseBuildHeroFragment,
                BaseBuildHeroFragment.newInstance(idHero = idHero)
            )
        }
        is InfoAboutHeroScreenSideEffects.OnTeamsListScreen -> {
            navController.navigate(
                R.id.action_infoAboutHeroFragment_to_teamsListFragment,
                TeamsListFragment.newInstance(idHero = idHero)
            )
        }
        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when(event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(InfoAboutHeroScreenEvents.GetHeroInfo(idHero))
            }

            else -> {}
        }
    }
}

@Composable
private fun InfoAboutHeroScreenContent(
    uiState: InfoAboutHeroScreenUiState,
    onEvent: (InfoAboutHeroScreenEvents) -> Unit,
) {

}