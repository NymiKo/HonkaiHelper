package com.example.tanorami.info_about_hero.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.strings.R
import com.example.tanorami.base_build_hero.ui.BaseBuildHeroNavArguments
import com.example.tanorami.info_about_hero.presentation.InfoAboutHeroViewModel
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenEvents
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenSideEffects
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenUiState
import com.example.tanorami.info_about_hero.ui.components.AbilitiesListColumn
import com.example.tanorami.info_about_hero.ui.components.EidolonsListColumn
import com.example.tanorami.info_about_hero.ui.components.SplashArtHeroImage
import com.example.tanorami.teams.ui.TeamsFromUsersNavArguments
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.ui.components.button.BaseNextButton
import com.example.ui.components.text.BaseDefaultText
import kotlinx.serialization.Serializable


@Serializable
data class InfoAboutHeroNavArguments(val idHero: Int)

@Composable
fun InfoAboutHeroScreen(
    navArguments: InfoAboutHeroNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: InfoAboutHeroViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value

    InfoAboutHeroScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when(sideEffects) {
        InfoAboutHeroScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }
        is InfoAboutHeroScreenSideEffects.OnBaseBuildHeroScreen -> {
            navController.navigate(route = BaseBuildHeroNavArguments(idHero = sideEffects.idHero))
            viewModel.clearEffect()
        }
        is InfoAboutHeroScreenSideEffects.OnTeamsListScreen -> {
            navController.navigate(route = TeamsFromUsersNavArguments(idHero = sideEffects.idHero))
            viewModel.clearEffect()
        }
        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when(event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(InfoAboutHeroScreenEvents.GetHeroInfo(navArguments.idHero))
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
    Scaffold(
        topBar = {
            com.example.ui.components.top_app_bar.BaseCenterAlignedTopAppBar(
                title = uiState.heroModel?.name,
                onBack = { onEvent(InfoAboutHeroScreenEvents.OnBack) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            SplashArtHeroImage(
                splashArtHero = uiState.heroModel?.splashArt,
                elementHero = uiState.element?.image,
                pathHero = uiState.path?.image
            )

            AsyncImage(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(),
                model = when (uiState.heroModel?.rarity) {
                    false -> R.drawable.icon_4_stars
                    true -> R.drawable.icon_5_stars
                    else -> R.drawable.icon_4_stars
                },
                contentDescription = null
            )

            BaseDefaultText(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                text = uiState.heroModel?.story ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp
            )

            BaseNextButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                text = stringResource(id = R.string.build),
                onClick = {
                    onEvent(InfoAboutHeroScreenEvents.OnBaseBuildHeroScreen)
                }
            )

            BaseNextButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                text = stringResource(id = R.string.teams_from_users),
                onClick = {
                    onEvent(InfoAboutHeroScreenEvents.OnTeamsListScreen)
                }
            )

            AbilitiesListColumn(abilitiesList = uiState.abilitiesList)

            EidolonsListColumn(eidolonsList = uiState.eidolonsList)
        }
    }
}