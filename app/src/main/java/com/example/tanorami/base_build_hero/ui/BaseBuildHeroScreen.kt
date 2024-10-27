package com.example.tanorami.base_build_hero.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.base_build_hero.presentation.BaseBuildHeroViewModel
import com.example.tanorami.base_build_hero.presentation.models.BaseBuildHeroScreenEvents
import com.example.tanorami.base_build_hero.presentation.models.BaseBuildHeroScreenSideEffects
import com.example.tanorami.base_build_hero.presentation.models.BaseBuildHeroScreenUiState
import com.example.tanorami.base_build_hero.ui.components.CategoryBestEquipments
import com.example.tanorami.base_build_hero.ui.components.EquipmentImage
import com.example.tanorami.base_build_hero.ui.components.StatsListColumn
import com.example.tanorami.base_components.button.BaseNextButton
import com.example.tanorami.base_components.top_app_bar.BaseCenterAlignedTopAppBar
import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersNavArguments
import com.example.tanorami.core.theme.Blue
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.core.theme.Violet
import com.example.tanorami.info_about_decoration.ui.InfoAboutDecorationFragment
import com.example.tanorami.info_about_relic.ui.InfoAboutRelicFragment
import com.example.tanorami.info_about_weapon.ui.InfoAboutWeaponNavArguments
import com.example.tanorami.utils.OnLifecycleEvent
import kotlinx.serialization.Serializable

@Serializable
data class BaseBuildHeroNavArguments(val idHero: Int)

@Composable
fun BaseBuildHeroScreen(
    baseBuildHeroNavArguments: BaseBuildHeroNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: BaseBuildHeroViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val uiState = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(null).value

    BaseBuildHeroScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        BaseBuildHeroScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }

        is BaseBuildHeroScreenSideEffects.OnBuildsHeroFromUsersScreen -> {
            navController.navigate(route = BuildsHeroFromUsersNavArguments(idHero = sideEffects.idHero))
            viewModel.clearEffect()
        }
        is BaseBuildHeroScreenSideEffects.OnInfoAboutDecorationScreen -> {
            navController.navigate(
                R.id.action_baseBuildHeroFragment_to_decorationInfoFragment,
                InfoAboutDecorationFragment.newInstance(sideEffects.idDecoration)
            )
            viewModel.clearEffect()
        }
        is BaseBuildHeroScreenSideEffects.OnInfoAboutRelicScreen -> {
            navController.navigate(
                R.id.action_baseBuildHeroFragment_to_relicInfoFragment,
                InfoAboutRelicFragment.newInject(sideEffects.idRelic)
            )
            viewModel.clearEffect()
        }
        is BaseBuildHeroScreenSideEffects.OnInfoAboutWeaponScreen -> {
            navController.navigate(route = InfoAboutWeaponNavArguments(idWeapon = sideEffects.idWeapon))
            viewModel.clearEffect()
        }

        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(
                    BaseBuildHeroScreenEvents.GetFullBaseBuildHero(
                        baseBuildHeroNavArguments.idHero
                    )
                )
            }

            else -> {}
        }
    }
}

@Composable
private fun BaseBuildHeroScreenContent(
    uiState: BaseBuildHeroScreenUiState,
    onEvent: (BaseBuildHeroScreenEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            BaseCenterAlignedTopAppBar(
                title = stringResource(id = R.string.build),
                onBack = { onEvent(BaseBuildHeroScreenEvents.OnBack) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            CategoryBestEquipments(
                equipmentsList = uiState.weaponsList,
                headerCategory = R.string.best_weapons,
                equipmentImage = { weapon ->
                    EquipmentImage(
                        modifier = Modifier
                            .width(72.dp)
                            .height(100.dp)
                            .background(
                                color = when (weapon.rarity) {
                                    0 -> Blue
                                    1 -> Violet
                                    2 -> Orange
                                    else -> Color.Transparent
                                },
                                shape = RoundedCornerShape(10.dp)
                            ),
                        equipmentImage = weapon.image,
                        onClick = { onEvent(BaseBuildHeroScreenEvents.OnInfoAboutWeaponScreen(weapon.idWeapon)) }
                    )
                }
            )

            CategoryBestEquipments(
                modifier = Modifier.padding(top = 16.dp),
                headerCategory = R.string.best_relics,
                equipmentsList = uiState.relicsList,
                equipmentImage = { relic ->
                    EquipmentImage(
                        modifier = Modifier
                            .size(90.dp)
                            .background(color = Orange, shape = RoundedCornerShape(10.dp)),
                        equipmentImage = relic.image,
                        onClick = { onEvent(BaseBuildHeroScreenEvents.OnInfoAboutRelicScreen(relic.idRelic)) }
                    )
                }
            )

            CategoryBestEquipments(
                modifier = Modifier.padding(top = 16.dp),
                headerCategory = R.string.best_decorations,
                equipmentsList = uiState.decorationsList,
                equipmentImage = { decoration ->
                    EquipmentImage(
                        modifier = Modifier
                            .size(90.dp)
                            .background(color = Orange, shape = RoundedCornerShape(10.dp)),
                        equipmentImage = decoration.image,
                        onClick = { onEvent(BaseBuildHeroScreenEvents.OnInfoAboutDecorationScreen(decoration.idDecoration)) }
                    )
                }
            )

            StatsListColumn(
                modifier = Modifier.padding(top = 16.dp),
                statsList = uiState.buildStatsEquipment
            )

            BaseNextButton(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.builds_from_users),
                onClick = { onEvent(BaseBuildHeroScreenEvents.OnBuildsHeroFromUsersScreen) }
            )
        }
    }
}