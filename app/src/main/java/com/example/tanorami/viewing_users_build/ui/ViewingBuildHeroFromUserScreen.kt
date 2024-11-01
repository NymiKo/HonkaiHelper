package com.example.tanorami.viewing_users_build.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.base_components.top_app_bar.BaseTopAppBar
import com.example.tanorami.create_build_hero.ui.components.AvatarHeroImageAndName
import com.example.tanorami.create_build_hero.ui.components.EquipmentBuildComponent
import com.example.tanorami.info_about_decoration.ui.InfoAboutDecorationNavArguments
import com.example.tanorami.info_about_relic.ui.InfoAboutRelicNavArgument
import com.example.tanorami.info_about_weapon.ui.InfoAboutWeaponNavArguments
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.tanorami.utils.toast
import com.example.tanorami.viewing_users_build.presentation.ViewingBuildHeroFromUserViewModel
import com.example.tanorami.viewing_users_build.presentation.models.ViewingBuildHeroFromUserScreenEvents
import com.example.tanorami.viewing_users_build.presentation.models.ViewingBuildHeroFromUserScreenSideEffects
import com.example.tanorami.viewing_users_build.presentation.models.ViewingBuildHeroFromUserScreenUiState
import com.example.tanorami.viewing_users_build.ui.components.BuildStatsColumn
import kotlinx.serialization.Serializable

@Serializable
data class ViewingBuildHeroFromUserNavArguments(val idBuild: Long)

@Composable
fun ViewingBuildHeroFromUserScreen(
    navArguments: ViewingBuildHeroFromUserNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: ViewingBuildHeroFromUserViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value
    val context = LocalContext.current

    ViewingBuildHeroFromUserScreenContent(uiState = state, onEvent = viewModel::onEvent)

    when (sideEffects) {
        ViewingBuildHeroFromUserScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }
        ViewingBuildHeroFromUserScreenSideEffects.OnCopyUIDClick -> {
            val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipData: ClipData = ClipData.newPlainText("UID", state.buildHero.uid)
            clipboard.setPrimaryClip(clipData)
            toast(context, R.string.message_uid_build_copied)
            viewModel.clearEffect()
        }

        is ViewingBuildHeroFromUserScreenSideEffects.OnInfoAboutDecorationScreen -> {
            navController.navigate(route = InfoAboutDecorationNavArguments(sideEffects.idDecoration))
            viewModel.clearEffect()
        }

        is ViewingBuildHeroFromUserScreenSideEffects.OnInfoAboutRelicScreen -> {
            navController.navigate(route = InfoAboutRelicNavArgument(sideEffects.idRelic))
            viewModel.clearEffect()
        }
        is ViewingBuildHeroFromUserScreenSideEffects.OnInfoAboutWeaponScreen -> {
            navController.navigate(route = InfoAboutWeaponNavArguments(sideEffects.idWeapon))
            viewModel.clearEffect()
        }

        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(ViewingBuildHeroFromUserScreenEvents.GetHeroBuild(navArguments.idBuild))
            }

            else -> {}
        }
    }
}

@Composable
private fun ViewingBuildHeroFromUserScreenContent(
    uiState: ViewingBuildHeroFromUserScreenUiState,
    onEvent: (ViewingBuildHeroFromUserScreenEvents) -> Unit
) {
    Scaffold(
        topBar = {
            BaseTopAppBar(
                title = uiState.buildHero.hero?.let {
                    stringResource(
                        id = R.string.hero_build_from,
                        it.name,
                        uiState.buildHero.nickname
                    )
                } ?: "",
                actions = {
                    Icon(
                        modifier = Modifier.clickable {
                            onEvent(ViewingBuildHeroFromUserScreenEvents.OnCopyUIDClick)
                        },
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                },
                onBack = { onEvent(ViewingBuildHeroFromUserScreenEvents.OnBack) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AvatarHeroImageAndName(
                heroImage = uiState.buildHero.hero?.localAvatarPath,
                heroName = uiState.buildHero.hero?.name,
                heroRarity = uiState.buildHero.hero?.rarity
            )

            EquipmentBuildComponent(
                weapon = uiState.buildHero.weapon?.toEquipment(),
                relicTwoParts = uiState.buildHero.relicTwoParts?.toEquipment(),
                relicFourParts = uiState.buildHero.relicFourParts?.toEquipment(),
                decoration = uiState.buildHero.decoration?.toEquipment(),
                onWeaponClick = { onEvent(ViewingBuildHeroFromUserScreenEvents.OnInfoAboutWeaponScreen) },
                onTwoPartsRelicClick = { onEvent(ViewingBuildHeroFromUserScreenEvents.OnTwoPartRelicClick) },
                onFourPartsRelicClick = { onEvent(ViewingBuildHeroFromUserScreenEvents.OnFourPartRelicClick) },
                onDecorationClick = { onEvent(ViewingBuildHeroFromUserScreenEvents.OnInfoAboutDecorationScreen) },
            )

            BuildStatsColumn(
                bodyStat = uiState.buildHero.statsEquipment[0],
                legsStat = uiState.buildHero.statsEquipment[1],
                sphereStat = uiState.buildHero.statsEquipment[2],
                ropeStat = uiState.buildHero.statsEquipment[3]
            )
        }
    }
}