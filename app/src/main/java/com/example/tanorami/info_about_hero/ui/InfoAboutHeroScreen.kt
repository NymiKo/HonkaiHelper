package com.example.tanorami.info_about_hero.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_build_hero.ui.BaseBuildHeroFragment
import com.example.tanorami.base_components.BaseButton
import com.example.tanorami.base_components.BaseCenterAlignedTopAppBar
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.info_about_hero.presentation.InfoAboutHeroViewModel
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenEvents
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenSideEffects
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenUiState
import com.example.tanorami.info_about_hero.ui.components.AbilitiesListColumn
import com.example.tanorami.info_about_hero.ui.components.EidolonsListColumn
import com.example.tanorami.info_about_hero.ui.components.SplashArtHeroImage
import com.example.tanorami.teams.ui.TeamsListFragment
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
            viewModel.clearEffect()
        }
        is InfoAboutHeroScreenSideEffects.OnTeamsListScreen -> {
            navController.navigate(
                R.id.action_infoAboutHeroFragment_to_teamsListFragment,
                TeamsListFragment.newInstance(idHero = idHero)
            )
            viewModel.clearEffect()
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
    Scaffold(
        topBar = {
            BaseCenterAlignedTopAppBar(
                title = uiState.hero?.name,
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
                splashArtHero = uiState.hero?.splashArt,
                elementHero = uiState.element?.image,
                pathHero = uiState.path?.image
            )

            AsyncImage(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(),
                model = when(uiState.hero?.rarity) {
                    false -> R.drawable.icon_4_stars
                    true-> R.drawable.icon_5_stars
                    else -> R.drawable.icon_4_stars
                },
                contentDescription = null
            )

            BaseDefaultText(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                text = uiState.hero?.story ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp
            )

            BaseButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                text = stringResource(id = R.string.build),
                onClick = {
                    onEvent(InfoAboutHeroScreenEvents.OnBaseBuildHeroScreen)
                }
            )

            BaseButton(
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